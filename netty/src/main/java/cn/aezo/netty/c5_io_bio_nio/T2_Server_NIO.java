package cn.aezo.netty.c5_io_bio_nio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.LinkedList;

public class T2_Server_NIO {

    /**
     * 1.NIO: new io/nonblocking io
     * 2.通过一个线程完成客户端连接、读取所有客户端连接发送过来的数据
     * 3.通过strace跟踪，日志如下
     *
     * rt_sigaction(SIGRT_30, {sa_handler=0x7f2d39312e00, sa_mask=[], sa_flags=SA_RESTORER, sa_restorer=0x7f2d3d4485d0}, {sa_handler=0x7f2d39559e50, sa_mask=[], sa_flags=SA_RESTORER, sa_restorer=0x7f2d3d4485d0}, 8) = 0
     * accept(5, 0x7f2d340f2070, [28])         = -1 EAGAIN (Resource temporarily unavailable)
     * write(1, "null...", 7)                  = 7
     */
    public static void main(String[] args) throws Exception {
        LinkedList<SocketChannel> clients = new LinkedList<>();

        ServerSocketChannel ss = ServerSocketChannel.open();
        ss.bind(new InetSocketAddress(9090));
        ss.configureBlocking(false); // 设置为非阻塞

        while (true) {
            SocketChannel client = ss.accept(); // 不会阻塞。内核accept执行时，无客户端连接则返回-1 => NULL；有客户端连接则返回文件描述符如fd5 => client object
            if (client == null) {
                System.out.println("null...");
            } else {
                client.configureBlocking(false);
                int port = client.socket().getPort();
                System.out.println("client port: " + port);
                clients.add(client);
            }

            ByteBuffer buffer = ByteBuffer.allocateDirect(4096); // 可以在堆内/堆外

            // 遍历已经链接进来的客户端能不能读写数据
            for (SocketChannel c : clients) { // 串行化读取，也可使用多线程
                /**
                 * 在客户端发送aaaaaaaaaaa，部分out日志如下(此时只有一个客户端连接)，其中write为System.out.println产生的系统调用
                 *
                 * accept(5, 0x7f08480f4c10, [28])         = -1 EAGAIN (Resource temporarily unavailable)
                 * read(6, 0x7f084979dfd0, 4096)           = -1 EAGAIN (Resource temporarily unavailable)
                 * accept(5, 0x7f08480f4c10, [28])         = -1 EAGAIN (Resource temporarily unavailable)
                 * read(6, "aaaaaaaaaaa\n", 4096)          = 12
                 * write(1, "59030: aaaaaaaaaaa\n", 19)    = 19
                 * write(1, "\n", 1)                       = 1
                 * accept(5, 0x7f08480f4c10, [28])         = -1 EAGAIN (Resource temporarily unavailable)
                 * read(6, 0x7f084979fff0, 4096)           = -1 EAGAIN (Resource temporarily unavailable)
                 */
                int num = c.read(buffer); // 不会阻塞
                if (num > 0) {
                    buffer.flip();
                    byte[] data = new byte[buffer.limit()];
                    buffer.get(data);
                    String b = new String(data);
                    // System.out.println(new String(buffer.array(), 0, num));

                    System.out.println(c.socket().getPort() + ": " + b);
                    buffer.clear();
                }
            }
        }
    }

}
