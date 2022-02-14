package com.bnaio.netty.chatroom.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @author miclefengzss
 * 2021/10/25 上午9:31
 */
public class Server {

    private int port;

    public Server(int port) {
        this.port = port;
    }

    public void run() throws Exception {
        // bossGroup 只处理连接请求
        EventLoopGroup bossGroup = new NioEventLoopGroup(1); // (1)
        // workerGroup 处理客户端业务，含有子线程 NioEventLoop 的个数默认为 CPU 核数的两倍
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            // 创建服务端启动对象
            ServerBootstrap b = new ServerBootstrap(); // (2)
            b.group(bossGroup, workerGroup)
                    // 使用 NioServerSocketChannel 作为服务器的通道实现
                    .channel(NioServerSocketChannel.class) // (3)
                    // 初始化服务器连接队列大小，服务端处理客户端连接请求是顺序处理的，所以同一时间只能处理一个客户端连接
                    // 多个客户端同时来到时，服务端将不能处理的客户端连接请求放到队列中等待处理
                    .option(ChannelOption.SO_BACKLOG, 1024)          // (4)
                    .childOption(ChannelOption.SO_KEEPALIVE, true) // (5)
                    // 创建通道初始化对象，设置初始化参数
                    .childHandler(new ChannelInitializer<SocketChannel>() { // (6)
                        @Override
                        public void initChannel(SocketChannel ch) throws Exception {
                            // 对 workerGroup 的 SocketChannel 设置处理器
                            ch.pipeline().addLast(new ServerHandler());
                        }
                    });

            // Bind and start to accept incoming connections.
            System.out.println("netty server start...");
            ChannelFuture f = b.bind(port).sync(); // (7)

            // 注册监听器
            f.addListener((ChannelFutureListener) channelFuture -> {
                if (f.isSuccess()) {
                    System.out.println("监听端口" + port + "成功");

                } else {
                    System.out.println("监听端口" + port + "失败");
                }
            });

            // 对通道关闭进行监听，closeFuture 是异步操作，监听通道关闭
            // 通过 sync 方法同步等待通道关闭处理完毕，阻塞等待通道关闭完成
            f.channel().closeFuture().sync();
        } finally {
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }
    }

    public static void main(String[] args) throws Exception {
        int port = 8080;
        if (args.length > 0) {
            port = Integer.parseInt(args[0]);
        }

        new Server(port).run();
    }
}
