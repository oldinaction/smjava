package cn.aezo.netty.c1_bio_nio_aio_netty.t2_nio;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author smalle
 * @date 2020-06-15 21:28
 */
public class PoolServer {
    private static ExecutorService pool = Executors.newFixedThreadPool(10);

    public static void main(String[] args) throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open(); // java.nio.channels.ServerSocketChannel
        serverSocketChannel.socket().bind(new InetSocketAddress("127.0.0.1", 8889));
        serverSocketChannel.configureBlocking(false); // 配置为非阻塞
        System.out.println("server started, listening on :" + serverSocketChannel.getLocalAddress());

        Selector selector = Selector.open();
        // 将Selector注册到ServerSocketChannel，让Selector负责监控是否有客户端连接上来(OP_ACCEPT)
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        while (true) {
            selector.select(); // 会在此处阻塞
            Set<SelectionKey> selectionKeys = selector.selectedKeys(); // 可以认为是此次需要处理的事件
            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            while (iterator.hasNext()) {
                SelectionKey selectionKey = iterator.next();
                iterator.remove();
                if (selectionKey.isAcceptable()) {
                    ServerSocketChannel server = (ServerSocketChannel) selectionKey.channel();
                    SocketChannel channel = server.accept();
                    channel.configureBlocking(false);
                    channel.register(selector, SelectionKey.OP_READ);
                } else if (selectionKey.isReadable()) {
                    // 位运算，移除此事件，iterator.remove()
                    selectionKey.interestOps(selectionKey.interestOps()&(~SelectionKey.OP_READ));
                    pool.execute(new ThreadHandlerChannel(selectionKey));
                }
            }
        }
    }

    static class ThreadHandlerChannel implements Runnable {
        private SelectionKey selectionKey;

        public ThreadHandlerChannel(SelectionKey selectionKey) {
            this.selectionKey = selectionKey;
        }

        @Override
        public void run() {
            SocketChannel channel = (SocketChannel) selectionKey.channel();
            ByteBuffer buffer = ByteBuffer.allocate(1024); // java.nio.ByteBuffer
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            try {
                // 将客户端的消息读到一个字节数组中
                int size = 0;
                while ((size = channel.read(buffer)) > 0) {
                    buffer.flip(); // 如果需要重复使用ByteBuffer时，需要进行读写交替
                    baos.write(buffer.array(),0, size);
                    buffer.clear();
                }
                baos.close();

                // 再将这个数据写回去
                byte[] content = baos.toByteArray();
                ByteBuffer writeBuf = ByteBuffer.allocate(content.length);
                writeBuf.put(content);
                writeBuf.flip();
                channel.write(writeBuf);
                if(size == -1) {
                    channel.close();
                } else {
                    selectionKey.interestOps(selectionKey.interestOps()|SelectionKey.OP_READ);
                    selectionKey.selector().wakeup();
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
