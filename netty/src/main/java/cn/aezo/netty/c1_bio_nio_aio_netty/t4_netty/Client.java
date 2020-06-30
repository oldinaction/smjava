package cn.aezo.netty.c1_bio_nio_aio_netty.t4_netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.CharsetUtil;
import io.netty.util.ReferenceCountUtil;

/**
 * @author smalle
 * @date 2020-06-15 22:38
 */
public class Client {

    public static void main(String[] args) {
        Bootstrap bootstrap = new Bootstrap();

        EventLoopGroup eventLoopGroup = new NioEventLoopGroup();

        try {
            ChannelFuture channelFuture = bootstrap.group(eventLoopGroup)
                    .channel(NioSocketChannel.class)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            System.out.println("initChannel: " + ch);

                            ch.pipeline().addLast(new WorkHandle());
                        }
                    })
                    .connect("127.0.0.1", 8891); // 异步方法，此步骤并不能保证是否能连接上

            channelFuture.addListener(new ChannelFutureListener() {
                @Override
                public void operationComplete(ChannelFuture future) throws Exception {
                    if(future.isSuccess()) {
                        System.out.println("connected...");
                    } else {
                        System.out.println("not connected...");
                    }
                }
            });

            channelFuture.sync();
            System.out.println("...");

            channelFuture.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            eventLoopGroup.shutdownGracefully();
        }
    }

    static class WorkHandle extends ChannelInboundHandlerAdapter {
        @Override
        public void channelActive(ChannelHandlerContext ctx) throws Exception {
            System.out.println("channel active...");

            ChannelFuture future = ctx.writeAndFlush(Unpooled.copiedBuffer("hello netty...".getBytes()));
            future.addListener(new ChannelFutureListener() {
                @Override
                public void operationComplete(ChannelFuture future) throws Exception {
                    if(future.isSuccess()) {
                        System.out.println("msg send...");
                    }
                }
            });
        }

        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
            try {
                ByteBuf byteBuf = (ByteBuf)msg;
                System.out.println("server msg: " + byteBuf.toString(CharsetUtil.UTF_8));
                // ctx.close();
            } finally {
                ReferenceCountUtil.release(msg); // 释放ByteBuf
            }
        }
    }
}
