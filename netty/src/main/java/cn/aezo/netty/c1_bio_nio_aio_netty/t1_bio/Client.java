package cn.aezo.netty.c1_bio_nio_aio_netty.t1_bio;

import java.io.IOException;
import java.net.Socket;

/**
 * @author smalle
 * @date 2020-06-15 12:42
 */
public class Client {
    public static void main(String[] args) throws IOException {
        Socket s = new Socket("127.0.0.1", 8888);
        // 一般不需要绑定本地端口，底层会自动随机使用一个端口
        // s = new Socket("api.weixin.qq.com", 443, InetAddress.getByName("192.168.17.237"), 9021); // 绑定本地端口
        // s.bind(new InetSocketAddress(9022)); // 绑定本地端口
        s.getOutputStream().write("hello bio...".getBytes());
        s.getOutputStream().flush();
        // s.getOutputStream().close();
        System.out.println("write over, waiting for msg back...");

        byte[] bytes = new byte[1024];
        int len = s.getInputStream().read(bytes); // 阻塞方法
        System.out.println(new String(bytes, 0, len));
        s.close();
    }
}
