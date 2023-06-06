package com.socket.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.CharsetUtil;
import org.junit.Test;

import java.net.InetSocketAddress;

/**
 * @Description: some desc
 * @Author: miclefengzss
 * @Date: 2023/4/13 14:48
 */
public class MyNetty {

    public static void main(String[] args) {
    }

    @Test
    public void myByteBuf() {
//        ByteBuf buf = ByteBufAllocator.DEFAULT.buffer(8, 20);
        //pool
//        ByteBuf buf = UnpooledByteBufAllocator.DEFAULT.heapBuffer(8, 20);
        ByteBuf buf = PooledByteBufAllocator.DEFAULT.heapBuffer(8, 20);

        print(buf);
        buf.writeBytes(new byte[]{1, 2, 3, 4});
        print(buf);
        buf.writeBytes(new byte[]{1, 2, 3, 4});
        print(buf);
        buf.writeBytes(new byte[]{1, 2, 3, 4});
        print(buf);
        buf.writeBytes(new byte[]{1, 2, 3, 4});
        print(buf);
        buf.writeBytes(new byte[]{1, 2, 3, 4});
        print(buf);
    }

    private static void print(ByteBuf buf) {
        System.out.println("buf.isReadable()    :" + buf.isReadable());
        System.out.println("buf.readerIndex()   :" + buf.readerIndex());
        System.out.println("buf.readableBytes() :" + buf.readableBytes());
        System.out.println("buf.isWritable()    :" + buf.isWritable());
        System.out.println("buf.writerIndex()   :" + buf.writerIndex());
        System.out.println("buf.writableBytes() :" + buf.writableBytes());
        System.out.println("buf.capacity()      :" + buf.capacity());
        System.out.println("buf.maxCapacity()   :" + buf.maxCapacity());
        System.out.println("buf.isDirect()      :" + buf.isDirect());
        System.out.println("---------------------------------------");
    }


    /**
     * 1、连接服务器
     * 2、主动发送数据
     * 3、收取数据
     * event_poll selector
     */
    @Test
    public void nettySimpleClientMode() throws Exception {
        NioEventLoopGroup threadSelector = new NioEventLoopGroup(1);

        // 客户端模式:
        NioSocketChannel client = new NioSocketChannel();

        // channel 注册事件到 selector  =>  epoll_ctl(7,OP_ADD,4
        threadSelector.register(client);

        // 预埋处理事件来临时的 handler
        ChannelPipeline pipeline = client.pipeline();
        pipeline.addLast(new MyInInHandler());

        // reactor 异步的特征，连接是异步的
        ChannelFuture connect = client.connect(new InetSocketAddress("127.0.0.1", 9999));
        // 等待连接成功
        ChannelFuture connectSync = connect.sync();

        String msg = "HelloServer";
        ByteBuf buf = Unpooled.copiedBuffer(msg.getBytes());
        System.out.println("发送数据: " + msg);
        // 发送数据也是异步的
        ChannelFuture writeFuture = client.writeAndFlush(buf);
//        writeFuture.sync();

        // 对通道关闭进行监听，closeFuture 是异步操作，监听通道关闭
        // 通过 sync 方法同步等待通道关闭处理完毕，阻塞等待通道关闭完成
        connectSync.channel().closeFuture().sync();

        System.out.println("client over ....");
    }

    @Test
    public void nettySimpleServerMode() throws Exception {
        NioEventLoopGroup threadSelector = new NioEventLoopGroup();

        NioServerSocketChannel server = new NioServerSocketChannel();

        // channel 注册事件 selector
        threadSelector.register(server);

        // 预埋处理事件来临时的 handler
        ChannelPipeline pipeline = server.pipeline();

        // 服务端需要先处理 accept 监听事件，然后还需要处理 OP_READ 事件
        pipeline.addLast(new MyAcceptHandler(threadSelector, new MyChannelInit()));

        ChannelFuture listen = server.bind(new InetSocketAddress("127.0.0.1", 9999)).sync();

        // 注册监听器
        listen.addListener((ChannelFutureListener) channelFutureListener -> {
            if (channelFutureListener.isSuccess()) {
                System.out.println("服务端监听 [9999] 成功。");
            } else {
                System.out.println("服务端监听 [9999] 失败。");
            }
        });

        // 对通道关闭进行监听，closeFuture 是异步操作，监听通道关闭
        // 通过 sync 方法同步等待通道关闭处理完毕，阻塞等待通道关闭完成
        listen.channel().closeFuture().sync();
    }

    @Test
    public void nettyClient() {
        try {
            NioEventLoopGroup worker = new NioEventLoopGroup(1);

            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(worker)
                    .channel(NioSocketChannel.class)
                    .handler(new MyInInHandler());

            ChannelFuture connectFuture = bootstrap.connect(new InetSocketAddress("127.0.0.1", 9999)).sync();

            connectFuture.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void nettyServer() {
        try {
            NioEventLoopGroup boss = new NioEventLoopGroup(1);
            NioEventLoopGroup worker = new NioEventLoopGroup();

            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(boss, worker)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<NioSocketChannel>() {
                        @Override
                        protected void initChannel(NioSocketChannel ch) throws Exception {
                            ChannelPipeline pipeline = ch.pipeline();
                            pipeline.addLast(new MyInInHandler());
                        }
                    });

            ChannelFuture listener = bootstrap.bind(new InetSocketAddress("127.0.0.1", 9999)).sync();

            listener.addListener((ChannelFutureListener) listen -> {
                if (listen.isSuccess()) {
                    System.out.println("监听端口: 9999 成功。");
                } else {
                    System.out.println("监听端口: 9999 失败。");
                }
            });

            listener.channel().closeFuture().sync();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

/**
 * 处理 accept 事件 并注册 OP_READ 事件.
 */
class MyAcceptHandler extends ChannelInboundHandlerAdapter {
    private final NioEventLoopGroup selector;
    private final ChannelHandlerAdapter handler;

    public MyAcceptHandler(NioEventLoopGroup selector, ChannelHandlerAdapter handler) {
        this.selector = selector;
        this.handler = handler;
    }

    // 服务端需要进行 listen socket accept 和 socket R/W ，两者都是通过  channelHandler.channelRead 读取
    // 第一次读取出来的是 listen 的 accept 事件
    // 如果是 accept 事件，channelRead 读出来的 msg 是 socketShannel 然后还需要进行注册 OP_READ 事件
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        NioSocketChannel client = (NioSocketChannel) msg;
        ChannelPipeline pipeline = client.pipeline();
        pipeline.addLast(handler);
        selector.register(client);
    }
}

/**
 * 包装一个单例处理的 channel_handler，为后续的 OP_READ 事件添加所需的 channel_handler
 */
@ChannelHandler.Sharable
class MyChannelInit extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        System.out.println("客户端:[" + channel.remoteAddress() + "] 连接成功。");
        ChannelPipeline pipeline = channel.pipeline();
        // pipeline => [MyChannelInit, MyInInHandler]
        pipeline.addLast(new MyInInHandler());
        // 后续读取不需要再执行此 handler 所以移除掉以提高后续读取效率 , pipeline => [MyInInHandler]
        pipeline.remove(this);
    }
}

/**
 * 处理 OP_READ 的事件的  channelHandler
 */
class MyInInHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        System.out.println("client registered.");
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("client active.");
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf buf = (ByteBuf) msg;
        // read 会移动指针，不可重复读取
//        CharSequence message = buf.readCharSequence(buf.readableBytes(), CharsetUtil.UTF_8);
        // get 不会移动指针，可重复读取
//        CharSequence message = buf.getCharSequence(0, buf.readableBytes(), CharsetUtil.UTF_8);
        String message = buf.toString(CharsetUtil.UTF_8);
        System.out.print("消息是: " + message);
        System.out.println("地址: " + ctx.channel().remoteAddress());
        System.out.println();
        ctx.writeAndFlush(buf);
    }
}
