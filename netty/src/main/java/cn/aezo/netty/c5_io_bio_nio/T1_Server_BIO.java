package cn.aezo.netty.c5_io_bio_nio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class T1_Server_BIO {

    /**
     * 使用脚本启动，通过strace记录系统调用情况
     *
     * rm -rf *out*
     * javac T1_Socket_BIO.java
     * strace -ff -o out java T1_Socket_BIO
     */
    public static void main(String[] args) throws Exception {
        ServerSocket server = new ServerSocket(9090,20);

        System.out.println("step1: new ServerSocket(9090)");

        while (true) {
            /**
             * 1.查看主进程out日志显示如下，程序卡死在poll处
             *
             * write(1, "step1: new ServerSocket(9090)", 29) = 29
             * write(1, "\n", 1)                       = 1
             * lseek(3, 58774522, SEEK_SET)            = 58774522
             * read(3, "PK\3\4\n\0\0\10\0\0\346\201\223Ky\271LV\2416\0\0\2416\0\0\25\0\0\0", 30) = 30
             * lseek(3, 58774573, SEEK_SET)            = 58774573
             * read(3, "\312\376\272\276\0\0\0004\1\345\n\0\6\1\27\t\0\233\1\30\t\0\233\1\31\t\0\233\1\32\t\0"..., 13985) = 13985
             * poll([{fd=6, events=POLLIN|POLLERR}], 1, -1
             *
             * 2.当有一个客户端连接上后，out日志显示如下(接着poll后面进行输出)
             * 主要有`accept(6,...`接受客户端连接; `clone(child_stack=...) = 7185`创建新线程; `poll(...`继续阻塞等待其他客户端连接
             *
             * poll([{fd=6, events=POLLIN|POLLERR}], 1, -1) = 1 ([{fd=6, revents=POLLIN}])
             * accept(6, {sa_family=AF_INET6, sin6_port=htons(43396), inet_pton(AF_INET6, "::ffff:192.168.6.135", &sin6_addr), sin6_flowinfo=htonl(0), sin6_scope_id=0}, [28]) = 7
             * fcntl(7, F_GETFL)                       = 0x2 (flags O_RDWR)
             * fcntl(7, F_SETFL, O_RDWR)               = 0
             * lseek(3, 62478664, SEEK_SET)            = 62478664
             * read(3, "PK\3\4\n\0\0\10\0\0\341\201\223K9\267\215\270R\6\0\0R\6\0\0;\0\0\0", 30) = 30
             * lseek(3, 62478753, SEEK_SET)            = 62478753
             * read(3, "\312\376\272\276\0\0\0004\0:\7\0!\n\0\v\0\"\t\0\10\0#\n\0\1\0$\t\0\v\0"..., 1618) = 1618
             * write(1, "client port: 43396", 18)     = 18
             * ......
             * mprotect(0x7f2a74172000, 4096, PROT_READ|PROT_WRITE) = 0
             * mprotect(0x7f2a74173000, 4096, PROT_READ|PROT_WRITE) = 0
             * mmap(NULL, 1052672, PROT_READ|PROT_WRITE, MAP_PRIVATE|MAP_ANONYMOUS|MAP_STACK, -1, 0) = 0x7f2a5f54b000
             * clone(child_stack=0x7f2a5f64afb0, flags=CLONE_VM|CLONE_FS|CLONE_FILES|CLONE_SIGHAND|CLONE_THREAD|CLONE_SYSVSEM|CLONE_SETTLS|CLONE_PARENT_SETTID|CLONE_CHILD_CLEARTID, parent_tidptr=0x7f2a5f64b9d0, tls=0x7f2a5f64b700, child_tidptr=0x7f2a5f64b9d0) = 7185
             * futex(0x7f2a7400a154, FUTEX_WAIT_PRIVATE, 35, NULL) = 0
             * futex(0x7f2a7400a128, FUTEX_WAIT_PRIVATE, 2, NULL) = 0
             * futex(0x7f2a7400a128, FUTEX_WAKE_PRIVATE, 1) = 0
             * poll([{fd=6, events=POLLIN|POLLERR}], 1, -1
             */
            Socket client = server.accept();  // 阻塞1
            System.out.println("client port: " + client.getPort());

            new Thread(() -> {
                InputStream in = null;
                try {
                    in = client.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                    while(true){
                        /**
                         * 1.查看7185线程的out日志，发现阻塞在`recvfrom(7,`
                         *
                         * sched_getaffinity(7185, 32, [0])        = 32
                         * mmap(0x7f2a5f54b000, 12288, PROT_READ|PROT_WRITE, MAP_PRIVATE|MAP_FIXED|MAP_ANONYMOUS, -1, 0) = 0x7f2a5f54b000
                         * mprotect(0x7f2a5f54b000, 12288, PROT_NONE) = 0
                         * lseek(3, 30053469, SEEK_SET)            = 30053469
                         * read(3, "PK\3\4\n\0\0\10\0\0\346\201\223K\24w\0067E\3\0\0E\3\0\0\27\0\0\0", 30) = 30
                         * lseek(3, 30053522, SEEK_SET)            = 30053522
                         * read(3, "\312\376\272\276\0\0\0004\0-\t\0\6\0\34\n\0\7\0\35\t\0\32\0\36\n\0\37\0\33\n\0"..., 837) = 837
                         * lseek(3, 30055252, SEEK_SET)            = 30055252
                         * read(3, "PK\3\4\n\0\0\10\0\0\360\201\223K\305SF\t\265\r\0\0\265\r\0\0 \0\0\0", 30) = 30
                         * lseek(3, 30055314, SEEK_SET)            = 30055314
                         * read(3, "\312\376\272\276\0\0\0004\0\242\n\0Y\0Z\n\0-\0[\t\0,\0\\\t\0,\0]\t\0"..., 3509) = 3509
                         * futex(0x7f2a740afb54, FUTEX_WAKE_OP_PRIVATE, 1, 1, 0x7f2a740afb50, FUTEX_OP_SET<<28|0<<12|FUTEX_OP_CMP_GT<<24|0x1) = 1
                         * futex(0x7f2a740afb28, FUTEX_WAKE_PRIVATE, 1) = 1
                         * recvfrom(7,
                         *
                         * 2.在客户端输入消息123回车发送给服务端，此时7185的out日志如下(接着上面recvfrom输出)
                         *
                         * recvfrom(7, "123\n", 8192, 0, NULL, NULL) = 4
                         * ioctl(7, FIONREAD, [0])                 = 0
                         * write(1, "123", 3)                      = 3
                         * write(1, "\n", 1)                       = 1
                         * recvfrom(7,
                         */
                        String dataline = reader.readLine(); // 阻塞2

                        if(null != dataline){
                            System.out.println(dataline);
                        }else{
                            client.close();
                            break;
                        }
                    }
                    System.out.println("客户端断开");

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}
