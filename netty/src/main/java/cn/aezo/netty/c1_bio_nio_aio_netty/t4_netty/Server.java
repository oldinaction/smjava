package cn.aezo.netty.c1_bio_nio_aio_netty.t4_netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.util.CharsetUtil;

/**
 * netty:
 * 多线程处理，可异步或同步
 * 传输数据均用ByteBuf(拥有读和写两个指针，使用堆外内存，注意释放)
 * ServerBootstrap、EventLoopGroup、ChannelInitializer、ChannelInboundHandlerAdapter
 *
 * @author smalle
 * @date 2020-06-15 23:06
 */
public class Server {
    public static void main(String[] args) {
        // 启动辅助类
        ServerBootstrap bootstrap = new ServerBootstrap();

        // 线程池，基于ScheduledExecutorService实现，用于处理Channel上的所有事件，如connect和read
        EventLoopGroup bossGroup = new NioEventLoopGroup(1); // 负责连接客户端
        EventLoopGroup workerGroup = new NioEventLoopGroup(2); // 负责处理客户端连接之外的请求

        try {
            ChannelFuture channelFuture = bootstrap.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class) // NioServerSocketChannel表示非阻塞处理，也可进行阻塞处理，传入阻塞类即可
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            // 当有客户端连接上来便会打印
                            System.out.println("initChannel: " + ch);

                            // pipeline使用责任链模式
                            ch.pipeline().addLast(new ChannelInboundHandlerAdapter() {
                                @Override
                                public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                                    // 读数据
                                    ByteBuf byteBuf = (ByteBuf) msg;
                                    System.out.println("client msg: " + byteBuf.toString(CharsetUtil.UTF_8));
                                    System.out.println("byteBuf结构: " + byteBuf); // PooledUnsafeDirectByteBuf(ridx: 0, widx: 14, cap: 1024) 分别为读指针，写指针，容量
                                    System.out.println("byteBuf引用数1: " + byteBuf.refCnt()); // 1

                                    // 写数据。writeAndFlush默认会释放ByteBuf的堆外内存，此时不能再手动释放
                                    ctx.writeAndFlush(msg);
                                    System.out.println("byteBuf引用数2: " + byteBuf.refCnt()); // 0, writeAndFlush释放了ByteBuf
                                    // ReferenceCountUtil.release(msg); // finally 中手动释放内存

                                    // ctx.close(); // 关闭channel，会用closeFuture继续执行
                                }
                            });
                        }
                    })
                    .bind(8891) // 异步方法，此步骤并不能保证绑定到主机端口
                    .sync(); // 阻塞方法，直到绑定到主机端口。除了sync，其他基本都是异步方法
            System.out.println("server started...");

            // 阻塞方法，只要服务端或客户端调用ctx.close();方法时才继续往下执行，否则客户端连接上后main方法就结束退出了
            channelFuture.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
