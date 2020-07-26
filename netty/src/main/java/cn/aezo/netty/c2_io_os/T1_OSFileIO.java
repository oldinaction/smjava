package cn.aezo.netty.c2_io_os;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author smalle
 * @date 2020-06-24 08:01
 */
public class T1_OSFileIO {

    static byte[] data = "123456789\n".getBytes();
    static String path = "/root/io-test/out.txt";

    public static void main(String[] args) throws Exception {

        switch (args[0]) {
            case "0":
                // 循环写，会发现效率低于testBufferedFileIO
                testBasicFileIO();
                break;
            case "1":
                testBufferedFileIO();
                break;
            case "2":
                testRandomAccessFileWrite();
            case "3":
                testWhatByteBuffer();
            default:
        }
    }

    /**
     * 1.编写如下启动脚本测试testBasicFileIO和testBufferedFileIO
     * rm -rf *out* # 为strace记录的日志文件(有多个线程所有有多个文件)，其中存储使用空间多的为主线程
     * javac T1_OSFileIO.java # 需提前去掉此类中的包名
     * strace -ff -o out java T1_OSFileIO $1 # 其中strace可监控用户空间进程和内核的交互，比如系统调用，将其输出到out
     *
     * 2.testBasicFileIO对应out文件日志如(每次都会调用系统方法写入到文件；java程序启动后是多线程的，如GC线程，一般主线程是进程PID+1)
     * open("/root/io-test/out.txt", O_WRONLY|O_CREAT|O_TRUNC, 0666) = 5 # 获取到/root/io-test/out.txt的此进程文件描述符为5
     * fstat(5, {st_mode=S_IFREG|0644, st_size=0, ...}) = 0
     * write(5, "123456789\n", 10)             = 10 # 对5号文件描述符(实际对应文件)进行写入
     * write(5, "123456789\n", 10)             = 10
     * write(5, "123456789\n", 10)             = 10
     * ...
     *
     * 3.testBufferedFileIO对应日志文件如(写满8字节才会调用系统的write方法写入到文件)
     * open("/root/io-test/out.txt", O_WRONLY|O_CREAT|O_TRUNC, 0666) = 5
     * fstat(5, {st_mode=S_IFREG|0644, st_size=0, ...}) = 0
     * ...
     * write(5, "123456789\n123456789\n123456789\n12"..., 8190) = 8190
     * write(5, "123456789\n123456789\n123456789\n12"..., 8190) = 8190
     * ...
     *
     * 4.由上述可见testBufferedFileIO的系统调用少，因此效率更高
     *
     * @throws Exception
     */
    // 最基本的file写
    public static void testBasicFileIO() throws Exception {
        File file = new File(path);
        FileOutputStream out = new FileOutputStream(file);
        while (true) {
            // Thread.sleep(10);
            out.write(data);
            // out.flush();
        }
    }
    // 测试buffer文件IO。jvm会分配8kB的内存，当内存写满时会进行system_call write(8KB byte[])写到page cache
    public static void testBufferedFileIO() throws Exception {
        File file = new File(path);
        BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(file));
        while (true) {
            // Thread.sleep(10);
            out.write(data);
            // out.flush();
        }
    }

    // 测试文件NIO
    public static void testRandomAccessFileWrite() throws Exception {
        RandomAccessFile raf = new RandomAccessFile(path, "rw");

        // 普通(顺序)写
        raf.write("hello world\n".getBytes());
        System.out.println("write------------");
        // 阻塞住程序，回车继续运行
        // 查看文件中的内容为 hello world(但是此时可能并不在磁盘上，只是在page cache上，还没有做刷写)
        // 此时通过`lsof -op <pid>`可发现此进程多了一个对该文件的读写文件描述符，如4u
        System.in.read();

        // 随机写，将指针指到第5个字符后。可理解为文件描述符的偏移量
        raf.seek(5);
        raf.write(",hi".getBytes());
        System.out.println("seek---------");
        System.in.read(); // 此时文件内容为 hello,hirld

        FileChannel rafChannel = raf.getChannel();
        // 调用系统mmap，进行linux进程堆外(设置大小为4096字节)和文件(page cache)的映射，此时是基于byte的读写，不是基于java的object
        // 只有文件才能进行mmap映射
        MappedByteBuffer map = rafChannel.map(FileChannel.MapMode.READ_WRITE, 0, 4096);

        // 不是系统调用，但是数据会到达内核的page cache
        /**
         * 1.曾经我们是需要out.write()这样的系统调用，才能让程序的data进入内核的page cache，必须有用户态内核态切换
         * 2.mmap的内存映射，依然是内核的page cache体系所约束的。换言之，可能丢数据
         * 3.可以去github上找一些其他C程序员写的jni扩展库，使用linux内核的Direct IO(直接IO)
         *   Direct IO是把page cache交给了程序自己开辟一个字节数组当作page cache(私有)，不经过系统读写page cache
         *   因此需要动用代码逻辑来维护一致性/dirty等一系列复杂问题，程序可以控制何时将page cache写入磁盘，但是从page cache写入磁盘的过程还是系统控制
         */
        map.put("@@@".getBytes());
        System.out.println("map-put--------");
        // 此时文件内容为 @@@lo,hirld
        // 此时通过`lsof -op <pid>`可发现此进程多了一个对该文件的内存映射：
        // java    7737 root  mem    REG              253,0            33621134 /root/io-test/out.txt
        // 并且文件的大小变成了4096(文件对应的page cache和堆外进行了映射，因此大小为上面设置的)
        // 到此，有上文4u和此处的mem都可以对此文件进行读写
        System.in.read();
        // map.force(); // flush

        // 随机写，将指针指到第0个字符后
        raf.seek(0);

        ByteBuffer buffer = ByteBuffer.allocate(8192);
        // ByteBuffer buffer = ByteBuffer.allocateDirect(1024); // 分配jvm堆外内存(但在linux进程堆内)

        int read = rafChannel.read(buffer); // 将rafChannel对应的文件内容读到buffer中，类似调用buffer.put()
        System.out.println("read: " + read); // 打印 read: 1024
        System.out.println(buffer); // java.nio.DirectByteBuffer[pos=4096 lim=8192 cap=8192]

        buffer.flip(); // ByteBuffer读写交替
        System.out.println(buffer); // java.nio.DirectByteBuffer[pos=0 lim=4096 cap=8192]
        for (int i = 0; i < buffer.limit(); i++) {
            Thread.sleep(200);
            System.out.print(((char) buffer.get(i)));
        }
    }

    public static void testWhatByteBuffer() {
        // ByteBuffer buffer = ByteBuffer.allocate(1024);
        ByteBuffer buffer = ByteBuffer.allocateDirect(1024); // 分配jvm堆外内存(但在linux进程堆内)

        System.out.println("position: " + buffer.position()); // 0，读写下标位置
        System.out.println("limit: " + buffer.limit()); //,1024，能读写的下标最大位置(每次执行flip会在position和capacity间切换)
        System.out.println("capacity: " + buffer.capacity()); // 1024，容量
        System.out.println("mark: " + buffer); // java.nio.DirectByteBuffer[pos=0 lim=1024 cap=1024] // 允许从0位置开始写入到1024位置

        buffer.put("123".getBytes()); // 往buffer中写入数据
        System.out.println("-------------put:123......");
        System.out.println("mark: " + buffer); // java.nio.DirectByteBuffer[pos=3 lim=1024 cap=1024]

        buffer.flip(); // 读写交替(修改limit指向的位置)。则位置从0开始可最大读取到3位置(总共为3个字节)
        // 如果此时执行一次写入
            // 则flip下面打印java.nio.DirectByteBuffer[pos=1 lim=3 cap=1024]
            // get(返回2)下面打印java.nio.DirectByteBuffer[pos=2 lim=3 cap=1024]
            // compact下面打印java.nio.DirectByteBuffer[pos=1 lim=1024 cap=1024] // 前两个位置指针已经移动经过了
        // buffer.put("4".getBytes());
        System.out.println("-------------flip......");
        System.out.println("mark: " + buffer); // java.nio.DirectByteBuffer[pos=0 lim=3 cap=1024]

        byte c = buffer.get(); // 读取一个字节
        System.out.println("-------------get......" + (char) c);
        System.out.println("mark: " + buffer); // java.nio.DirectByteBuffer[pos=1 lim=3 cap=1024]

        buffer.compact(); // 压缩。因为已经读走1个字节，还剩下2个字节，此时再写入时则可以从第2个位置开始写到1024个位置
        System.out.println("-------------compact......");
        System.out.println("mark: " + buffer); // java.nio.DirectByteBuffer[pos=2 lim=1024 cap=1024]

        buffer.clear(); // 清空buffer
        System.out.println("-------------clear......");
        System.out.println("mark: " + buffer); // java.nio.DirectByteBuffer[pos=0 lim=1024 cap=1024]
    }

}
