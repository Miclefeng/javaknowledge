package com.javase.io.nio.chatroom.server;

import java.io.Closeable;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.Set;

/**
 * @author miclefengzss
 * 2021/9/30 下午3:08
 */
public class ChatServer {

    public static final int DEFAULT_PORT = 8888;
    public static final String QUIT = "quit";
    public static final int BUFFER = 1024;

    private Integer port;

    private ServerSocketChannel serverSocketChannel;

    private Selector selector;

    private ByteBuffer readBuffer = ByteBuffer.allocate(BUFFER);
    private ByteBuffer writeBuffer = ByteBuffer.allocate(BUFFER);

    private Charset charset = StandardCharsets.UTF_8;

    public ChatServer() {
        this(DEFAULT_PORT);
    }

    public ChatServer(int port) {
        this.port = port;
    }

    private void start() {
        try {
            /**
             * 1. 创建Selector
             */
            selector = Selector.open();
            /**
             * 2. 通过 ServerSocketChannel 创建channel
             */
            serverSocketChannel = ServerSocketChannel.open();
            /**
             * 3. 为 channel 绑定监听端口
             */
            serverSocketChannel.socket().bind(new InetSocketAddress(port));
            /**
             * 4. ** 设置channel为非阻塞模式 **
             */
            serverSocketChannel.configureBlocking(false);
            /**
             * 5. 将 channel 注册到 selector 上，监听连接事件
             */
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
            /**
             * 6. 循环等待新接入的连接
             */
            for (; ; ) {
                // 获取可用 channel 数量
                final int readyChannels = selector.select();

                // 防止 selector 的空轮循
                if (readyChannels == 0) {
                    continue;
                }
                /**
                 * 获取可用 channel 的集合
                 */
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                Iterator<SelectionKey> iterator = selectionKeys.iterator();
                while (iterator.hasNext()) {
                    /**
                     * 获取 selectionKey 实例
                     */
                    SelectionKey selectionKey = iterator.next();
                    /**
                     * 移除set中的当前的selectionKey
                     */
                    iterator.remove();
                    /**
                     * 7. 根据监听就绪状态，调用对应的方法处理业务逻辑
                     */
                    handles(selectionKey, selector);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            /**
             * 关闭 selector 会将已注册的 channel 关闭断开
             */
            close(selector);
        }
    }

    private void handles(SelectionKey selectionKey, Selector selector) throws IOException {
        // ACCEPT 事件 - 和客户端建立了连接
        if (selectionKey.isAcceptable()) {
            ServerSocketChannel serverSocketChannel = (ServerSocketChannel) selectionKey.channel();
            /**
             * 如果是接入事件，创建 socketChannel
             */
            SocketChannel socketChannel = serverSocketChannel.accept();
            /**
             * 将 socketChannel 设置为非阻塞模式
             */
            socketChannel.configureBlocking(false);
            /**
             * 将 channel 注册到 selector 上，监听可读事件
             */
            socketChannel.register(selector, SelectionKey.OP_READ);
            System.out.println(getClientName(socketChannel) + " 已连接。");
            /**
             * 回复客户端提示信息
             */
            socketChannel.write(charset.encode("你与聊天室的其他人不是好友关系，请注意隐身安全！"));
        }

        // READ 事件 - 客户端发送了消息
        if (selectionKey.isReadable()) {
            /**
             * 从 selectionKey 中获取到已经就绪的 channel
             */
            SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
            /**
             *  处理客户端发送的信息
             */
            String forwardMessage = receive(socketChannel);
            System.out.println(getClientName(socketChannel) + " : " + forwardMessage);
            /**
             * 将 channel 再次注册到 selector 上，监听它的可读事件
             */
            socketChannel.register(selector, SelectionKey.OP_READ);

            // 客户端异常处理
            if (forwardMessage.isEmpty()) {
                // 取消当前 channel 在 selector 上的监听
                selectionKey.cancel();

                // select() 是阻塞式的，通过 wakeup() 通知 selector 重新审视当前监听的事件
                selector.wakeup();
            } else {
                /**
                 * 将客户端发送的请求信息 广播给其他客户端
                 */
                forwardMessage(selector, socketChannel, forwardMessage);

                // 检查用户是否退出
                if (readyToQuit(forwardMessage)) {
                    selectionKey.cancel();
                    selector.wakeup();
                    System.out.println(getClientName(socketChannel) + " 已退出。");
                }
            }
        }
    }

    private String receive(SocketChannel socketChannel) throws IOException {
        /**
         * 创建 buffer
         */
        readBuffer.clear();
        StringBuilder content = new StringBuilder();
        /**
         * 循环读取客户端的请求信息
         */
        while (socketChannel.read(readBuffer) > 0) {
            // buffer 从写模式转换为读模式
            readBuffer.flip();
            // 读取 buffer 的内容
            content.append(charset.decode(readBuffer));
        }
        return content.toString();
    }

    private void forwardMessage(Selector selector, SocketChannel socketChannel, String forwardMessage) throws IOException {
        /**
         * 获取到所有已接入的客户端
         */
        for (SelectionKey selectionKey : selector.keys()) {
            Channel connectedClient = selectionKey.channel();
            if (connectedClient instanceof ServerSocketChannel) {
                continue;
            }
            if (selectionKey.isValid() && !socketChannel.equals(connectedClient)) {
                writeBuffer.clear();
                writeBuffer.put(charset.encode(getClientName(socketChannel) + ":" + forwardMessage));
                writeBuffer.flip();
                while (writeBuffer.hasRemaining()) {
                    ((SocketChannel) connectedClient).write(writeBuffer);
                }
            }
        }
    }

    private String getClientName(SocketChannel socketChannel) {
        return "客户端 [" + socketChannel.socket().getPort() + "]";
    }

    private boolean readyToQuit(String msg) {
        return QUIT.equals(msg);
    }

    private void close(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        final ChatServer chatServer = new ChatServer(7777);
        chatServer.start();
    }
}
