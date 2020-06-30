package cn.aezo.netty.c1_bio_nio_aio_netty.t3_aio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;

/**
 * aio:
 * 异步io(linux下本质还是使用nio，windows下的aio则不是使用nio)
 * AsynchronousServerSocketChannel#accept、AsynchronousSocketChannel#read
 *
 * @author smalle
 * @date 2020-06-15 21:28
 */
public class Server {

    public static void main(String[] args) throws IOException, InterruptedException {
        AsynchronousServerSocketChannel assc = AsynchronousServerSocketChannel.open()
                .bind(new InetSocketAddress("127.0.0.1", 8890));

        // 此时accept为非阻塞，相当于观察者模式，当有客户端连接时，系统后面会自动调用CompletionHandler#completed。因此下面需要while-sleep进行阻塞测试
        assc.accept(null, new CompletionHandler<AsynchronousSocketChannel, Object>() {
            @Override
            public void completed(AsynchronousSocketChannel client, Object attachment) {
                assc.accept(null, this); // 对客户端进行连接，第一次assc.accept可能并没有连接到客户端
                try {
                    System.out.println(client.getRemoteAddress());
                    ByteBuffer buffer = ByteBuffer.allocate(1024);
                    // 此时read也为非阻塞方法
                    client.read(buffer, buffer, new CompletionHandler<Integer, ByteBuffer>() {
                        @Override
                        public void completed(Integer result, ByteBuffer attachment) {
                            attachment.flip();
                            System.out.println(new String(attachment.array(), 0, result));
                            client.write(ByteBuffer.wrap("[aio] Hello Client".getBytes()));
                        }

                        @Override
                        public void failed(Throwable exc, ByteBuffer attachment) {}
                    });

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void failed(Throwable exc, Object attachment) {}
        });

        // 阻塞住程序，防止主程序退出
        while (true) {
            Thread.sleep(1000);
        }
    }
}
