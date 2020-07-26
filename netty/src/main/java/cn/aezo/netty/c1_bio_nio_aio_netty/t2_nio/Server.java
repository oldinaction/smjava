package cn.aezo.netty.c1_bio_nio_aio_netty.t2_nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * bio:
 * 全双工(可同时读写)
 * 多个连接可使用同一个线程
 * Selector负责循环监听socket的所有事件
 * 读写数据使用ByteBuffer(只有一个指针, 需要flip进行limit指针位置交替)
 * ServerSocketChannel#register(selector, SelectionKey.OP_ACCEPT);
 *
 * @author smalle
 * @date 2020-06-15 20:52
 */
public class Server {

    public static void main(String[] args) throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open(); // java.nio.channels.ServerSocketChannel
        serverSocketChannel.socket().bind(new InetSocketAddress("127.0.0.1", 8889));
        serverSocketChannel.configureBlocking(false); // 配置为非阻塞
        System.out.println("server started, listening on :" + serverSocketChannel.getLocalAddress());

        Selector selector = Selector.open();
        // 将Selector注册到ServerSocketChannel，让Selector负责监控是否有客户端连接上来(OP_ACCEPT)
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        while (true) {
            selector.select(); // 会在此处阻塞，也可设置超时时间，或者selector.wakeup唤醒
            Set<SelectionKey> selectionKeys = selector.selectedKeys(); // 获取所有监听Channel里面的事件
            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            while (iterator.hasNext()) {
                SelectionKey selectionKey = iterator.next();
                iterator.remove();
                handle(selectionKey);
            }
        }
    }

    private static void handle(SelectionKey key) throws IOException {
        if (key.isAcceptable()) {
            // 可以和客户端进行连接
            ServerSocketChannel serverSocketChannel = (ServerSocketChannel) key.channel();
            // 连接上了则返回一个Socket通道用于发送接收消息，每有一个连接就会有一个SocketChannel
            SocketChannel socketChannel = serverSocketChannel.accept(); // java.nio.channels.SocketChannel
            socketChannel.configureBlocking(false);

            // 将Selector注册到SocketChannel，让Selector负责监控是否有客户端是否有消息发送过来(OP_READ)
            socketChannel.register(key.selector(), SelectionKey.OP_READ);
        } else if(key.isReadable()) {
            // **没有消息，则不会进入到此代码块；有消息时才会是可读状态。不像bio一直阻塞在读取处**
            SocketChannel socketChannel = null;
            try {
                socketChannel = (SocketChannel)key.channel();
                // ByteBuffer只有一个指针，读写使用一个ByteBuffer时，需要注意进行指针重置
                ByteBuffer buffer = ByteBuffer.allocate(512); // java.nio.ByteBuffer
                buffer.clear();
                int len = socketChannel.read(buffer); // 阻塞方法，但是此时肯定是有数据可读的，因此影响不大

                if(len != -1) {
                    System.out.println(new String(buffer.array(), 0, len));
                }

                ByteBuffer bufferToWrite = ByteBuffer.wrap("HelloClient".getBytes());
                socketChannel.write(bufferToWrite);
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if(socketChannel != null) {
                    try {
                        socketChannel.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

    }

}
