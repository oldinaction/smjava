package cn.aezo.netty.c6_io_multiplexing;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class SocketMultiplexingSingleThreadV1 {
    private ServerSocketChannel server = null;
    private Selector selector = null; // linux 多路复用器(select poll epoll kqueue)，nginx  event{}

    /**
     * 1.lsof查看文件描述符如下
     *
     * ......
     * java    8882 root    3r      REG   253,0 0t60163512   716018 /opt/soft/jdk1.8.0_161/jre/lib/rt.jar
     * java    8882 root    4r      REG   253,0 0t17869852   776809 /opt/soft/jdk1.8.0_161/jre/lib/ext/jfxrt.jar
     * java    8882 root    5u     IPv6 1536835        0t0      TCP *:websm (LISTEN)
     * java    8882 root    6r     FIFO     0,9        0t0  1536836 pipe
     * java    8882 root    7w     FIFO     0,9        0t0  1536836 pipe
     * java    8882 root    8u  a_inode    0,10        0t0     6473 [eventpoll]
     * java    9001 root    9u     IPv6 1537603        0t0      TCP test1:websm->test2:59034 (ESTABLISHED) # 客户端连接上之后产生
     *
     * 2.strace日志如下
     *
     * lseek(3, 50611973, SEEK_SET)            = 50611973
     * read(3, "\312\376\272\276\0\0\0004\0/\n\0\n\0 \t\0\t\0!\t\0\t\0\"\t\0\t\0#\n\0"..., 958) = 958
     * epoll_create(256)                       = 8
     * lseek(3, 48484629, SEEK_SET)            = 48484629
     * ......
     * epoll_ctl(8, EPOLL_CTL_ADD, 6, {EPOLLIN, {u32=6, u64=14281070652122202118}}) = 0
     * lseek(3, 48682523, SEEK_SET)            = 48682523
     * read(3, "PK\3\4\n\0\0\10\0\0\353\201\223K^\277#\341\224\6\0\0\224\6\0\0!\0\0\0", 30) = 30
     * ......
     * write(1, "server start...", 15)         = 15
     * write(1, "\n", 1)                       = 1
     * write(1, "size: 1", 8)                 = 8
     * write(1, "\n", 1)                       = 1
     * lseek(3, 30319212, SEEK_SET)            = 30319212
     * ......
     * read(3, "\312\376\272\276\0\0\0004\0\t\7\0\7\7\0\10\1\0\tinterrupt\1\0\25("..., 162) = 162
     * epoll_ctl(8, EPOLL_CTL_ADD, 5, {EPOLLIN, {u32=5, u64=14422430571433033733}}) = 0
     * epoll_wait(8, [], 4096, 500)            = 0
     * write(1, "size: 1", 8)                 = 8
     * write(1, "\n", 1)                       = 1
     * epoll_wait(8, [], 4096, 500)            = 0
     * write(1, "size: 1", 8)                 = 8
     * write(1, "\n", 1)                       = 1
     * ......
     * accept(5, {sa_family=AF_INET6, sin6_port=htons(59032), inet_pton(AF_INET6, "::ffff:192.168.6.135", &sin6_addr), sin6_flowinfo=htonl(0), sin6_scope_id=0}, [28]) = 9
     * ......
     * write(1, "new client: 192.168.6.135:59", 29) = 29
     * write(1, "\n", 1)                       = 1
     * epoll_ctl(8, EPOLL_CTL_ADD, 9, {EPOLLIN, {u32=9, u64=6052996228860346377}}) = 0
     * epoll_wait(8, [], 4096, 500)            = 0
     * write(1, "2   size", 8)                 = 8
     * ......
     * socketpair(AF_UNIX, SOCK_STREAM, 0, [10, 11]) = 0
     * close(11)                               = 0
     * read(9, "Hello World\n", 8192)       = 15
     * lseek(3, 59033366, SEEK_SET)            = 59033366
     * read(3, "PK\3\4\n\0\0\10\0\0\353\201\223K\332^j*U\3\0\0U\3\0\0\31\0\0\0", 30) = 30
     * lseek(3, 59033421, SEEK_SET)            = 59033421
     * read(3, "\312\376\272\276\0\0\0004\0)\n\0\t\0&\7\0'\5\377\377\377\377\377\377\377\376\5\377\377\377\377"..., 853) = 853
     * write(9, "Hello World\n", 15)        = 15
     * read(9, 0x7f72540c59a0, 8192)           = -1 EAGAIN (Resource temporarily unavailable)
     * epoll_wait(8, [], 4096, 500)            = 0
     * ......
     */
    public static void main(String[] args) {
        SocketMultiplexingSingleThreadV1 service = new SocketMultiplexingSingleThreadV1();
        service.initServer();
        service.start();
    }

    public void initServer() {
        try {
            server = ServerSocketChannel.open();
            server.configureBlocking(false);
            /**
             * server类似listen状态的fd5
             */
            server.bind(new InetSocketAddress(9090));

            /**
             * 1.select/poll模型下：相当于在jvm内部创建对象保存
             * 2.epoll模型下：相当于调用epoll_create => 返回fd8。开辟一块内存空间(红黑树保存)，用来记录需要监听IO
             * 3.jvm默认使用epoll，但是可以使用-D参数修改；-Djava.nio.channels.spi.SelectorProvider=sun.nio.ch.PollSelectorProvider(EPollSelectorProvider)
             */
            selector = Selector.open();

            /**
             * 1.select/poll：jvm里开辟一个数组，将 fd8 放进去
             * 2.epoll：相当于调用 epoll_ctl(8, EPOLL_CTL_ADD, 5, {EPOLLIN，将fd5加入到fd8中进行监听
             * 3.日志中的 epoll_ctl(8, EPOLL_CTL_ADD, 6, {EPOLLIN 暂不清楚是个啥？？？
             */
            server.register(selector, SelectionKey.OP_ACCEPT);
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("server start...");
    }

    public void start() {
        try {
            while (true) {
                Set<SelectionKey> keys = selector.keys();
                System.out.println("size: " + keys.size());

                /**
                 * 1.调用多路复用器 select、poll、epoll(epoll_wait)
                 * 2.selector.select不带时间则一直阻塞(可使用selector.wakeup唤醒)，带时间则判断是否超时
                 * 3.懒加载：再触碰到selector.select()调用的时候触发了epoll_ctl的调用
                 */
                while (selector.select(500) > 0) {
                    Set<SelectionKey> selectionKeys = selector.selectedKeys(); // 返回的有状态的fd集合
                    Iterator<SelectionKey> iter = selectionKeys.iterator();
                    // 尽管是多路复用器，此处还是得一个个同步调用R/W
                    while (iter.hasNext()) {
                        SelectionKey key = iter.next();
                        iter.remove(); // 不移除会重复循环处理
                        if (key.isAcceptable()) {
                            /**
                             * 1.如果要去接受一个新的连接，那么accept接受连接且返回新连接的FD
                             * select/poll：因为他们内核没有空间，那么保存在jvm中，和fd8一起
                             * epoll：通过epoll_ctl把新的客户端fd注册到内核空间
                             */
                            acceptHandler(key);
                        } else if (key.isReadable()) {
                            /**
                             * 1.read调用会阻塞，因此出现了 IO THREADS (对有状态的FD新开线程进行读取，可结合线程池)，参考版本2
                             * 2.redis 用了epoll，redis也有io threads的概念
                             * 3.tomcat 8,9 异步的处理方式 IO 和处理上解耦
                             */
                            readHandler(key);
                        } else if(key.isWritable()) {
                            /**
                             * 1.多路复用器能不能写是参考send-queue有没有空间
                             * 即写事件，和send-queue(Send-Q)有关，只要是空的，就一定会返回可以写的事件，便会回调此处的写方法
                             * 2.因此read一开始就要注册，但是write需要在确实要写的时候注册(如果一开始就注册，则会一直被调用，进入死循环)
                             * 并且write完后需要注销selectionKey.cancel()
                             */
                            writeHandler(key);
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void acceptHandler(SelectionKey key) {
        System.out.println("accept handler.....");
        try {
            ServerSocketChannel ssc = (ServerSocketChannel) key.channel();
            SocketChannel client = ssc.accept(); // 调用accept接受客户端，返回fd9
            client.configureBlocking(false);

            ByteBuffer buffer = ByteBuffer.allocate(8192);
            // 再次调用register，同上，将fd9加入到内存空间
            client.register(selector, SelectionKey.OP_READ, buffer);
            System.out.println("new client: " + client.getRemoteAddress());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void readHandler(SelectionKey key) {
        System.out.println("read handler.....");
        SocketChannel client = (SocketChannel) key.channel();
        ByteBuffer buffer = (ByteBuffer) key.attachment();
        buffer.clear();
        int read = 0;
        try {
            while (true) {
                /**
                 * 客户端发送Hello World
                 */
                read = client.read(buffer);
                if (read > 0) {
                    // 此时需要写数据，则进行注册。注册之后会关心 OP_WRITE，其实就是关心send-queue是不是有空间，有则会触发写事件
                    client.register(key.selector(), SelectionKey.OP_WRITE, buffer);
                } else if (read == 0) {
                    break;
                } else {
                    client.close();
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void writeHandler(SelectionKey key) {
        System.out.println("write handler...");
        SocketChannel client = (SocketChannel) key.channel();
        ByteBuffer buffer = (ByteBuffer) key.attachment();
        buffer.flip();
        while (buffer.hasRemaining()) {
            try {
                client.write(buffer);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        buffer.clear();
        key.cancel();
        try {
            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
