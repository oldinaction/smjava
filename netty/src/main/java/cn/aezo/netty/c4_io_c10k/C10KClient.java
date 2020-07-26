package cn.aezo.netty.c4_io_c10k;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;
import java.util.LinkedList;

public class C10KClient {

    /**
     * 1.由于windows启动了VMware虚拟机，因此有两个IP：192.168.1.10对应物理网卡，192.168.6.1对应虚拟网卡
     * 虽然为同一客户机的相同端口，但是客户端IP不同，服务器会认为是两个连接(可以组成不同的四元组)
     *
     * 2.在windows中启动此程序，连接linux服务端，会发现打印如下
     * client port: 10000
     * client port: 10001
     * client port: 10002
     * ...
     * 每次只有一个客户端IP(192.168.6.1)连接成功，主要原因是服务器返回给客户端IP(192.168.1.10)的包被丢弃了。解决办法增加路由
     * route -n # 查看路由
     * route add -host 192.168.1.10 gw 192.168.6.1 # 添加路由
     *
     * 3.增加路由后，可看到的打印如下
     * client port: 10000
     * client port: 10000
     * client port: 10001
     * client port: 10001
     * ...
     *
     * 4.使用此客户端一次连接T1_Server_BIO、T2_Server_NIO发现：T2_Server_NIO连接的速度高于T1_Server_BIO
     *
     * 5.如果服务器提示打开资源超过上限，可通过如`ulimit -SHn 200000`设置可打开的文件上限为20W
     */
    public static void main(String[] args) {
        LinkedList<SocketChannel> clients = new LinkedList<>();
        InetSocketAddress serverAddr = new InetSocketAddress("192.168.6.134", 9090);

        for (int i = 10000; i < 65000; i++) {
            try {
                SocketChannel client1 = SocketChannel.open();
                client1.bind(new InetSocketAddress("192.168.6.1", i));
                client1.connect(serverAddr);
                client1.isOpen();
                clients.add(client1);

                SocketChannel client2 = SocketChannel.open();
                client2.bind(new InetSocketAddress("192.168.1.10", i));
                client2.connect(serverAddr);
                client2.isOpen();
                clients.add(client2);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        System.out.println("clients "+ clients.size());

        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
