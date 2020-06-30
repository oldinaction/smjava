package cn.aezo.netty.c1_bio_nio_aio_netty.t1_bio;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * bio
 * 半双工，不能同时读写
 * 每有一个连接就会启动一个线程(特别好资源)
 * ServerSocket#accept()、Socket
 *
 * @author smalle
 * @date 2020-06-15 12:42
 */
public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocket ss = new ServerSocket(8888);
        while(true) {
            Socket s = ss.accept(); // 阻塞方法，直到有客户端进行连接
            System.out.println("a client connected");

            // 每有一个客户端连接上来就需要启动一个线程进行处理客户端连接(Socket)
            new Thread(() -> {
                try {
                    byte[] bytes = new byte[1024]; // 每次读取1024个字节大小的数据
                    int len = s.getInputStream().read(bytes); // **阻塞方法，没有消息也需要等着，因此每个连接需要一个线程**
                    System.out.println(new String(bytes, 0, len));

                    Thread.sleep(3000);
                    s.getOutputStream().write(bytes, 0, len);
                    s.getOutputStream().flush();
                } catch (IOException | InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}
