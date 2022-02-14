package socket;

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
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * @author miclefengzss
 * 2021/10/11 下午2:04
 */
public class Client {

    public static final String DEFAULT_SERVER_HOST = "39.103.225.4";
    public static final int DEFAULT_SERVER_PORT = 50880;
    public static final int BUFFER = 1024;
    public static final String QUIT = "quit";

    private String host;
    private int port;
    private SocketChannel socketChannel;
    private ByteBuffer readBuffer = ByteBuffer.allocate(BUFFER);
    private ByteBuffer writeBuffer = ByteBuffer.allocate(BUFFER);
    private Selector selector;
    private Charset charset = StandardCharsets.UTF_8;

    public Client() {
        this(DEFAULT_SERVER_HOST, DEFAULT_SERVER_PORT);
    }

    public Client(String host, int port) {
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
                new Thread(() -> {
                    try {
                        List<short[]> commands = new ArrayList<>();
                        commands.add(new short[]{0xAA, 0xAA, 0x0D, 0x01, 0x01, 0x04, 0x57, 0xE0, 0x1D, 0xFF, 0xFF, 0x02, 0x01, 0x0F, 0xC3});
                        commands.add(new short[]{0xAA, 0xAA, 0x0D, 0x01, 0x01, 0x04, 0x57, 0xF1, 0x17, 0xFF, 0xFF, 0x01, 0x00, 0x53, 0x3B});
                        commands.add(new short[]{0xAA, 0xAA, 0x0F, 0x01, 0x01, 0x04, 0x57, 0xDA, 0x1F, 0xFD, 0xFD, 0xF8, 0x00, 0x16, 0x01, 0x72, 0x75});
                        commands.add(new short[]{0xAA, 0xAA, 0x0F, 0x01, 0x01, 0x04, 0x57, 0xDA, 0x1B, 0xFD, 0xFD, 0xF8, 0x00, 0x02, 0x02, 0x4C, 0x67});
                        commands.add(new short[]{0xAA, 0xAA, 0x0F, 0x01, 0x01, 0x04, 0x57, 0xDA, 0x1F, 0xFD, 0xFD, 0xF8, 0x00, 0x16, 0x01, 0x72, 0x75});

                        short[] register = new short[]{0xAA, 0xAA, 0x0D, 0x00, 0x00, 0xE2, 0xAA, 0x00, 0x00, 0x00, 0x00, 0x01, 0xF4, 0x7E, 0xF4};
                        sendCommand(socketChannel, Server.transformShortToByteArray(register));
                        Thread.sleep(1000);

                        short[] timeSync = new short[]{0xAA, 0xAA, 0x0C, 0x01, 0x01, 0x04, 0x57, 0xDA, 0x03, 0x00, 0x00, 0xF8, 0xB6, 0x1A};
//                        sendCommand(socketChannel, Server.transformShortToByteArray(timeSync));
//                        Thread.sleep(1000);

                        short[] loopControl = new short[]{0xAA, 0xAA, 0x10, 0x01, 0x04, 0x01, 0xA7, 0x00, 0x32, 0xFF, 0xFF, 0x01, 0xF8, 0x64, 0x04, 0x67, 0x88, 0x8F};
//                        sendCommand(socketChannel, Server.transformShortToByteArray(loopControl));
//                        Thread.sleep(1000);

                        short[] loopStatus = new short[]{0xAA, 0xAA, 0x10, 0x01, 0x04, 0x01, 0xA7, 0x00, 0x34, 0x00, 0x00, 0x04, 0x64, 0x64, 0x64, 0x00, 0x44, 0x1B};
//                        sendCommand(socketChannel, Server.transformShortToByteArray(loopStatus));
//                        Thread.sleep(1000);

                        short[] loopAlarm = new short[]{0xAA, 0xAA, 0x11, 0x01, 0x04, 0x01, 0xA7, 0xE0, 0xDC, 0x00, 0x00, 0xF8, 0x04, 0x04, 0x06, 0x06, 0x00, 0xF9, 0x0B};
//                        sendCommand(socketChannel, Server.transformShortToByteArray(loopAlarm));
//                        Thread.sleep(1000);

                        for (short[] c : commands) {
//                            sendCommand(socketChannel, Server.transformShortToByteArray(c));
//                            Thread.sleep(200);
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }).start();
                /**
                 * 将 channel 注册到 selector 上，监听可读事件
                 */
                socketChannel.register(selector, SelectionKey.OP_READ);
            }
        }

        // READ事件 - 服务器转发消息
        if (selectionKey.isReadable()) {
            SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
            byte[] message = receive(socketChannel);
            System.out.println("Time: " + System.currentTimeMillis() + " : " + Hex.byte2HexStr(message));
        }
    }

    private void sendCommand(SocketChannel socketChannel, byte[] command) {
        writeBuffer.clear();
        writeBuffer.put(command);
        writeBuffer.flip();
        try {
            System.err.println("Send Command: " + Hex.byte2HexStr(command));
            socketChannel.write(writeBuffer);
        } catch (IOException e) {
            System.err.println("Send Command Error:");
            e.printStackTrace();
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

    public byte[] receive(SocketChannel socketChannel) throws IOException {
        /**
         * 创建 buffer
         */
        readBuffer.clear();
        byte[] content = new byte[0];
        /**
         * 循环读取客户端的请求信息
         */
        while (socketChannel.read(readBuffer) > 0) {
            // buffer 从写模式转换为读模式
            readBuffer.flip();
            // 读取 buffer 的内容
            byte[] data = new byte[readBuffer.remaining()];
            readBuffer.get(data);
            content = unionByteArray(content, data);
            readBuffer.flip();
        }
        return content;
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
//        39.103.225.4
        final Client chatClient = new Client("127.0.0.1", 50880);
        chatClient.start();
//        System.out.println(858 % 256);
    }

    public static byte[] unionByteArray(byte[]... args) {
        int length = 0;
        for (byte[] a : args) {
            length += a.length;
        }
        byte[] c = new byte[length];
        int offset = 0;
        for (byte[] a : args) {
            System.arraycopy(a, 0, c, offset, a.length);
            offset += a.length;

        }
        return c;
    }
}
