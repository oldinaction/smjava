package cn.aezo.netty.c3_io_tcp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class T1_Server_Properties {

    // server socket listen property:
    // 见下文描述
    private static final int BACKLOG = 2;
    // 指服务器内核接收数据时缓冲的最大字节数(数据交给应用程序后，缓冲区数据会消失)。实际跟进操作系统可能以此基数进行设置缓冲区大小
    private static final int SO_RCVBUF = 10;
    /**
     * 1.SO_REUSEADDR allows your server to bind to an address which is in a TIME_WAIT state
     * 2.在TCP断开链接的时候我们需要四次握手来断开，而且当两端都关闭了read/write通道以后我们还是要等待一个TIME_WAIT时间。
     *   如果我们没有等到这个TIME_WAIT时间就让OS把这个端口释放给其他的进程使用，别的进程很有可能就会收到上一个会话的残余TCP包
     * 3.监听进程异常关闭，一般需要等到端口释放才能重新绑定，这样就会出现重启不成功。设置SO_REUSEADDR = true则无需等待直接监听
     */
    private static final boolean SO_REUSEADDR = false;
    /**
     * 1.SO_TIMEOUT表示ServerSocket的accept()方法等待客户连接的超时时间，以毫秒为单位。默认为0，表示永远不会超时
     * 2.当超时后，程序会抛出异常但是不会退出，会跳过阻塞继续允许。如下列会进入下一个循环继续阻塞等待客户端连接
     */
    private static final int SO_TIMEOUT = 0;

    // client socket listen property on server endpoint:
    /**
     * 1.开启KeepAlive功能后，就会自动在规定时间内向对方发送心跳包，而另一方在收到心跳包后就会自动回复，以告诉对方我仍然在线
     * 2.因为开启KeepAlive功能需要消耗额外的宽带和流量，所以TCP协议层默认并不开启KeepAlive功能，尽管这微不足道，但在按流量计费的环境下增加了费用
     */
    private static final boolean CLI_SO_KEEPALIVE = false;
    // 是否在一行发送。当开启延迟发送时，本身会将一些小包聚集到一定大小后一起发送的；但是此参数为false时，会先发送一个小包，然后后面的一起发送
    private static final boolean CLI_SO_OOBINLINE = false;
    private static final int CLI_SO_RCVBUF = 20;
    private static final boolean CLI_SO_REUSEADDR = false;
    // 类似SO_RCVBUF
    private static final int CLI_SO_SNDBUF = 20;
    /**
     * 1.在默认情况下，当调用close关闭socket时，close会立即返回。但是如果send buffer中还有数据，系统会试着先把send buffer中的数据发送出去，然后close才返回
     * 2.如果开启CLI_OS_LINGER，则等待CLI_OS_LINGER_N时间再close
     */
    private static final boolean CLI_OS_LINGER = true;
    private static final int CLI_OS_LINGER_N = 0;
    private static final int CLI_SO_TIMEOUT = 0;
    /**
     * 1.TCP/IP协议中针对TCP默认开启了Nagle算法。Nagle算法通过减少需要传输的数据包来优化网络。在内核实现中，数据包的发送和接受会先走缓存，分别对应于写缓存和读缓存
     * 2.启动TCP_NODELAY，就意味着禁用了Nagle算法，允许小包的发送。对于延时敏感型，同时数据传输量比较小的应用，可开启TCP_NODELAY
     */
    private static final boolean CLI_TCP_NODELAY = false;

    public static void main(String[] args) {
        ServerSocket server = null;
        try {
            server = new ServerSocket();
            server.bind(new InetSocketAddress(9090), BACKLOG);
            server.setReceiveBufferSize(SO_RCVBUF);
            server.setReuseAddress(SO_REUSEADDR);
            server.setSoTimeout(SO_TIMEOUT);
            System.out.println("server up use 9090!");
            /**
             * 在serverSocket.bind()之后，serverSocket.accept()之前
             * 1.查看监听状态
             * 1.1 `netstat -natp` 查看发现程序已经建立了监听
             * tcp6       0      0 :::9090                 :::*                    LISTEN      7257/java
             * 1.2 `lsof -op 7257` 查看文件描述符发现多了一个6u
             * java    7257 root    6u  IPv6              39469        0t0      TCP *:websm (LISTEN)
             *
             * 1.2 在服务端`tcpdump -nn -i ens33 port 9090`抓包，然后客户端此时发起连接，监听到如下数据报
             * (1) 完成了三次握手 (2) 可以看出TCP连接的过程：客户端SYN -> 服务端ACK,SYN -> 客户端ACK (3) win为窗口大小(就是告诉对方自己能接受多大的数据)
             * 21:46:11.563246 IP 192.168.6.135.55110 > 192.168.6.134.9090: Flags [S], seq 1672196869, win 29200, options [mss 1460,sackOK,TS val 1806833 ecr 0,nop,wscale 7], length 0
             * 21:46:11.563269 IP 192.168.6.134.9090 > 192.168.6.135.55110: Flags [S.], seq 2367976873, ack 1672196870, win 1152, options [mss 1460,sackOK,TS val 2091619 ecr 1806833,nop,wscale 0], length 0
             * 21:46:11.563673 IP 192.168.6.135.55110 > 192.168.6.134.9090: Flags [.], ack 1, win 229, options [nop,nop,TS val 1806834 ecr 2091619], length 0
             *
             * 1.3 此时`lsof -op 7257`查看发现多了一个ESTABLISHED连接(说明内核已经和客户端建立好了连接, 且XIP:XPort-YIP:YPort四元组socket已经创建)，但是并没有分配给某个线程(因为还没有执行serverSocket.accept)
             * tcp6       1      0 :::9090                 :::*                    LISTEN      7257/java # 当客户端通过 connect() 去连接正在 listen() 的服务端时，这些连接会一直处于这个 queue 里面直到被服务端 accept()。Recv-Q的数值为1即为等待accept的四元组数
             * tcp6       0      0 192.168.6.134:9090      192.168.6.135:55110     ESTABLISHED -
             *
             * 2.此时客户端(SocketClient.java)在控制台输入123并回车(给服务器发送一些包)
             * 2.1 服务端抓包如下(发了两个数据包，一个大小为1，一个大小为2。会出现提前有一个字节出来是因为客户端设置了SO_OOBINLINE=false)
             * 22:00:08.959380 IP 192.168.6.135.55110 > 192.168.6.134.9090: Flags [P.], seq 1:2, ack 1, win 229, options [nop,nop,TS val 2644230 ecr 2091619], length 1
             * 22:00:08.959407 IP 192.168.6.134.9090 > 192.168.6.135.55110: Flags [.], ack 2, win 1151, options [nop,nop,TS val 2929015 ecr 2644230], length 0
             * 22:00:08.959708 IP 192.168.6.135.55110 > 192.168.6.134.9090: Flags [P.], seq 2:4, ack 1, win 229, options [nop,nop,TS val 2644230 ecr 2929015], length 2
             * 22:00:09.000042 IP 192.168.6.134.9090 > 192.168.6.135.55110: Flags [.], ack 4, win 1149, options [nop,nop,TS val 2929056 ecr 2644230], length 0
             *
             * 2.2 此时`lsof -op 7257`查看发现Recv-Q的数值为3(说明内核已经接收了客户端发送的数据并且缓存在Recv-Q，但是没有任何进程接受)
             * tcp6       1      0 :::9090                 :::*                    LISTEN      7257/java
             * tcp6       3      0 192.168.6.134:9090      192.168.6.135:55110     ESTABLISHED -
             *
             * 3.服务端回车，会往下执行serverSocket.accept
             * 3.1 此时服务端打印
             * client port: 55110
             * client read some data is :3 val :123 # 说明数据被内核缓存了，并且此时将2个包数据合并后交给了用户进程
             *
             * 3.2 此时`lsof -op 7257`查看发现ESTABLISHED对应的四元组已经绑定了进程，且Recv-Q中的数据清空了
             * tcp6       0      0 :::9090                 :::*                    LISTEN      7257/java
             * tcp6       0      0 192.168.6.134:9090      192.168.6.135:55110     ESTABLISHED 7257/java
             *
             * 3.3 `lsof -op 7257` 查看文件描述符发现多了一个7u
             * java    7257 root    7u  IPv6              39955        0t0      TCP test1:websm->test2:55110 (ESTABLISHED)
             *
             * 4.客户端再次输入456，服务端打印，且服务端抓包仍然是2个包(一个大小为1，一个大小为2)
             * client read some data is :1 val :4
             * client read some data is :2 val :56
             *
             * 5.由于serverSocket.bind的backlog为2
             * 已经被服务端accept的不算；当进来3个客户端连接时先进行处理第一个，然后将其他2个放到队列中；当第4个连接进行时，由于队列已满，因此无法创建连接，但是内核已经收到，因此状态为SYN_RECV
             * 等过一定时间还未变成ESTABLISHED，此连接请求会被丢弃，客户端报错Connection reset
             * tcp6       3      0 :::9090                 :::*                    LISTEN      7257/java
             * tcp6       0      0 192.168.6.134:9090      192.168.6.135:55110     ESTABLISHED 7257/java
             * tcp6       0      0 192.168.6.134:9090      192.168.6.135:55116     ESTABLISHED -
             * tcp6       0      0 192.168.6.134:9090      192.168.6.135:55112     ESTABLISHED -
             * tcp6       0      0 192.168.6.134:9090      192.168.6.135:55114     ESTABLISHED -
             * tcp        0      0 192.168.6.134:9090      192.168.6.135:55118     SYN_RECV    -
             */
            while (true) {
                System.in.read(); // 阻塞，直到回车才会继续运行

                Socket client = server.accept(); // 阻塞
                System.out.println("client port: " + client.getPort());

                client.setKeepAlive(CLI_SO_KEEPALIVE);
                client.setOOBInline(CLI_SO_OOBINLINE);
                client.setReceiveBufferSize(CLI_SO_RCVBUF);
                client.setReuseAddress(CLI_SO_REUSEADDR);
                client.setSendBufferSize(CLI_SO_SNDBUF);
                client.setSoLinger(CLI_OS_LINGER, CLI_OS_LINGER_N);
                client.setSoTimeout(CLI_SO_TIMEOUT);
                client.setTcpNoDelay(CLI_TCP_NODELAY);

                new Thread(() -> {
                    try {
                        InputStream in = client.getInputStream();
                        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                        char[] data = new char[1024];
                        while (true) {
                            int num = reader.read(data); // 阻塞

                            if (num > 0) {
                                System.out.println("client read some data is :" + num + " val :" + new String(data, 0, num));
                            } else if (num == 0) {
                                System.out.println("client read nothing!");
                                continue;
                            } else {
                                System.out.println("client read -1...");
                                System.in.read();
                                client.close();
                                break;
                            }
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }).start();

            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                server.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
