package com.javase.bnaio.nio.chatroom.client;

import java.io.Closeable;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ClosedSelectorException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.Set;

/**
 * @author miclefengzss
 * 2021/10/11 下午2:04
 */
public class ChatClient {

    public static final String DEFAULT_SERVER_HOST = "127.0.0.1";
    public static final int DEFAULT_SERVER_PORT = 8888;
    public static final int BUFFER = 1024;
    public static final String QUIT = "quit";

    private String host;
    private int port;
    private SocketChannel socketChannel;
    private ByteBuffer readBuffer = ByteBuffer.allocate(BUFFER);
    private ByteBuffer writeBuffer = ByteBuffer.allocate(BUFFER);
    private Selector selector;
    private Charset charset = StandardCharsets.UTF_8;

    public ChatClient() {
        this(DEFAULT_SERVER_HOST, DEFAULT_SERVER_PORT);
    }

    public ChatClient(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public void start() {
        try {
            /**
             * 创建 selector
             */
            selector = Selector.open();
            /**
             * 创建 socketChannel
             */
            socketChannel = SocketChannel.open();
            /**
             * 将 channel 设置为非阻塞模式
             */
            socketChannel.configureBlocking(false);
            /**
             * 将 channel 注册到 selector，监听 CONNECT 事件
             * 客户端连接到服务器，客户端的 channel 会触发一个 connect 事件
             */
            socketChannel.register(selector, SelectionKey.OP_CONNECT);
            /**
             * 向服务器端发起连接请求
             */
            socketChannel.connect(new InetSocketAddress(host, port));
            /**
             * 6. 循环等待新产生的事件
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
                    handles(selectionKey);
                }
            }
        } catch (IOException | ClosedSelectorException e) {
            e.printStackTrace();
        } finally {
            close(selector);
        }
    }

    private void handles(SelectionKey selectionKey) throws IOException {
        // CONNECT事件 - 连接就绪事件
        if (selectionKey.isConnectable()) {
            SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
            /**
             * 判断建立连接的操作是否到达就绪状态
             */
            if (socketChannel.isConnectionPending()) {
                /**
                 * 处理建立好的连接
                 */
                socketChannel.finishConnect();
                /**
                 * 处理用户的输入
                 */
                new Thread(new UserInputHandler(this)).start();
                /**
                 * 将 channel 注册到 selector 上，监听可读事件
                 */
                socketChannel.register(selector, SelectionKey.OP_READ);
            }
        }

        // READ事件 - 服务器转发消息
        if (selectionKey.isReadable()) {
            SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
            String message = receive(socketChannel);
            if (message.isEmpty()) {
                // 服务器异常
                close(selector);
            } else {
                System.out.println(message);
            }
        }
    }

    public void send(String msg) throws IOException {
        if (msg.isEmpty()) {
            return;
        }

        writeBuffer.clear();
        writeBuffer.put(charset.encode(msg));
        writeBuffer.flip();
        while (writeBuffer.hasRemaining()) {
            socketChannel.write(writeBuffer);
        }

        // 检查用户是否准备退出
        if (readyToQuit(msg)) {
            close(selector);
        }
    }

    public String receive(SocketChannel socketChannel) throws IOException {
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

    public boolean readyToQuit(String msg) {
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
        final ChatClient chatClient = new ChatClient("127.0.0.1", 7777);
        chatClient.start();
//        System.out.println(858 % 256);
    }
}
