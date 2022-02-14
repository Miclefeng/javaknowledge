package socket;

import java.io.*;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;

/**
 * @author miclefengzss
 * 2021/9/25 下午3:51
 */
public class Server {

    public static final Integer DEFAULT_PORT = 50880;

    public static final int BUFFER = 1024;

    public static boolean first = true;

    private Integer port;

    private ServerSocketChannel serverSocketChannel;

    private Selector selector;

    private ByteBuffer readBuffer = ByteBuffer.allocate(BUFFER);
    private ByteBuffer writeBuffer = ByteBuffer.allocate(BUFFER);

    private static final CRC crc = new CRC(CRC.Parameters.XMODEM);

    public Server() {
        this(DEFAULT_PORT);
    }

    public Server(int port) {
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
            System.out.println("Server 启动成功,监听端口[" + port + "]");
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
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        } finally {
            /**
             * 关闭 selector 会将已注册的 channel 关闭断开
             */
            close(selector);
        }
    }

    private void handles(SelectionKey selectionKey, Selector selector) throws IOException, InterruptedException {
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
//            socketChannel.write(charset.encode("你与聊天室的其他人不是好友关系，请注意隐身安全！"));
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
            new Thread(() -> {
                byte[] forwardMessage = new byte[]{};
                try {
                    forwardMessage = receive(socketChannel);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                final String[] sr = Hex.byte2HexStr(forwardMessage).split("AAAA");
                for (String s : sr) {
                    if ("AAAA".equals(s) || s.isEmpty()) {
                        continue;
                    }

                    System.out.println(getClientName(socketChannel) + " Receive Message: AAAA" + s);
                    if ("0D0000E2AA0000000001F47EF4".equals(s)) {
                        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(System.currentTimeMillis()));
                    }
                }
                // 客户端异常处理
                if (forwardMessage.length == 0) {
                    try {
                        Thread.sleep(5);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
//                    // 取消当前 channel 在 selector 上的监听
//                    selectionKey.cancel();
//
//                    // select() 是阻塞式的，通过 wakeup() 通知 selector 重新审视当前监听的事件
//                    selector.wakeup();
                }
            }).start();

            /**
             * 将 channel 再次注册到 selector 上，监听它的可读事件
             */
            socketChannel.register(selector, SelectionKey.OP_READ);

            short[] boot = new short[]{0xAA, 0xAA};
            short[] srcSubnetDeviceControl = new short[]{0x00, 0x00};
            short[] srcSubnetDeviceStrategy = new short[]{0xFD, 0xFD};
            short[] sourceDeviceType = new short[]{0xE2, 0xAA};
            short[] opCodeControl = new short[]{0x00, 0x31};
            short[] opCodeStrategy = new short[]{0xDA, 0x1A};
            short[] targetSubnet = new short[]{0x01};
            short[] targetSubNetDevice = new short[]{0x01, 0x01};
            // 修改时间
            Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            final NumberFormat numberInstance = NumberFormat.getNumberInstance();
            numberInstance.setMaximumIntegerDigits(2);
            year = Integer.parseInt(numberInstance.format(year));
            int month = calendar.get(Calendar.MONTH) + 1;
            int day = calendar.get(Calendar.DATE);
            int hour = calendar.get(Calendar.HOUR_OF_DAY);
            int minute = calendar.get(Calendar.MINUTE);
            int second = calendar.get(Calendar.SECOND);
            int weekDay = calendar.get(Calendar.DAY_OF_WEEK);
            if (weekDay == 1) {
                weekDay = 7;
            } else {
                weekDay -= 1;
            }
            short[] writeTime = unionShortArray(new short[]{0x12}, srcSubnetDeviceControl, sourceDeviceType, new short[]{0xDA, 0x02}, targetSubnet, new short[]{0x01}, new short[]{(short) year, (short) month, (short) day, (short) hour, (short) minute, (short) second, (short) weekDay});
            byte[] writeTimeCrc = buildCrc(writeTime);
            byte[] writeTimeCommand = unionByteArray(transformShortToByteArray(unionShortArray(boot, writeTime)), writeTimeCrc);
//            sendCommand(socketChannel, writeTimeCommand);
//            Thread.sleep(1000);
            // 读取时间
            short[] readTime = unionShortArray(new short[]{0x0B}, srcSubnetDeviceControl, sourceDeviceType, new short[]{0xDA, 0x00}, targetSubnet, new short[]{0x01});
            byte[] readTimeCrc = buildCrc(readTime);
            byte[] readTimeCommand = unionByteArray(transformShortToByteArray(unionShortArray(boot, readTime)), readTimeCrc);
//            sendCommand(socketChannel, readTimeCommand);
//                Thread.sleep(10000);
            if (first) {
                short[] loopStatus1 = unionShortArray(new short[]{0x0B}, srcSubnetDeviceControl, sourceDeviceType, new short[]{0xE0, 0xDB}, targetSubnet, new short[]{0x04});
                byte[] crc1 = buildCrc(loopStatus1);
//                sendCommand(socketChannel, unionByteArray(transformShortToByteArray(unionShortArray(boot, loopStatus1)), crc1));
//                Thread.sleep(500);
                short[] loopStatus2 = unionShortArray(new short[]{0x0B}, srcSubnetDeviceControl, sourceDeviceType, new short[]{0xE0, 0xDB}, targetSubnet, new short[]{0x05});
//                byte[] crc2 = buildCrc(loopStatus2);
//                sendCommand(socketChannel, unionByteArray(transformShortToByteArray(unionShortArray(boot, loopStatus2)), crc2));


                for (int i = 0; i < 12; i++) {
                    // 回复客户发送的消息
                    short[] targetDevice;
                    short[] loop = new short[1];
                    if (i >= 4) {
                        targetDevice = new short[]{0x05};
                        loop[0] = (short) (i - 3);
                    } else {
                        targetDevice = new short[]{0x04};
                        loop[0] = (short) (i + 1);
                    }
//                    if (i == 4) {
//                        continue;
//                    }
                    short[] open = new short[]{0x64, 0x00, 0x00};
                    short[] openLoop = unionShortArray(loop, open);
                    short[] length = new short[]{(short) (openLoop.length + 11)};
                    short[] openContent = unionShortArray(length, srcSubnetDeviceControl, sourceDeviceType, opCodeControl, targetSubnet, targetDevice, openLoop);
                    byte[] openCrcNum = buildCrc(openContent);
                    final byte[] openCmd = unionByteArray(transformShortToByteArray(unionShortArray(boot, openContent)), openCrcNum);
//                        sendCommand(socketChannel, openCmd);
//                        Thread.sleep(250);
                }

//                Thread.sleep(3000);
                for (int i = 0; i < 12; i++) {
                    // 回复客户发送的消息
                    short[] targetDevice;
                    short[] loop = new short[1];
                    if (i >= 4) {
                        if (i != 7) {
                            continue;
                        }
                        targetDevice = new short[]{0x05};
                        loop[0] = (short) (i - 3);
                    } else {
                        if (i != 3) {
                            continue;
                        }
                        targetDevice = new short[]{0x04};
                        loop[0] = (short) (i + 1);
                    }

                    short[] close = new short[]{0x00, 0x00, 0x00};
                    short[] closeLoop = unionShortArray(loop, close);
                    short[] length = new short[]{(short) (closeLoop.length + 11)};
                    short[] closeContent = unionShortArray(length, srcSubnetDeviceControl, sourceDeviceType, opCodeControl, targetSubnet, targetDevice, closeLoop);
                    byte[] closeCrcNum = buildCrc(closeContent);
                    final byte[] shutdown = unionByteArray(transformShortToByteArray(unionShortArray(boot, closeContent)), closeCrcNum);
//                    sendCommand(socketChannel, shutdown);
//                    Thread.sleep(250);
                }
                short[] strategyOpen = new short[]{0x0D, 0xFD, 0xFD, 0xE2, 0xAA, 0xE0, 0x1C, 0x01, 0x01, 0x0B, 0xFF};
                byte[] strategyOpenCrc = buildCrc(strategyOpen);
//                sendCommand(socketChannel, unionByteArray(transformShortToByteArray(unionShortArray(boot, strategyOpen)), strategyOpenCrc));
//                Thread.sleep(300);
                short[] strategyForbidden = new short[]{0x0D, 0xFD, 0xFD, 0xE2, 0xAA, 0xF1, 0x16, 0x01, 0x01, 0x01, 0x00};
                byte[] strategyForbiddenCrc = buildCrc(strategyForbidden);
                sendCommand(socketChannel, unionByteArray(transformShortToByteArray(unionShortArray(boot, strategyForbidden)), strategyForbiddenCrc));
                Thread.sleep(300);
                short[] logicTable1 = new short[]{0x00, 0x02, 0x01};
                short[] logicTable2 = new short[]{0x00, 0x02, 0x02};
                short[] logicTable3 = new short[]{0x00, 0x02, 0x03};
                short[] logicTable4 = new short[]{0x00, 0x02, 0x04};
                short[] logicTable5 = new short[]{0x00, 0x02, 0x05};
                short[] logicTable6 = new short[]{0x00, 0x02, 0x06};

                short[] logicOr = new short[]{0x02};
                short[] logicAnd = new short[]{0x01};

                short[] commonPre = new short[]{0x00, 0x00, 0x0A, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00};
                short[] commonPad = new short[]{0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00};

                short[] edit2 = new short[]{0x01, 0x01, 0x01, 0x05, 0x00, 0x00, 0x00, 0x00};
                short[] edit3 = new short[]{0x01, 0x02, 0x01, 0x05, 0x00, 0x00, 0x00, 0x00};
                short[] edit5 = new short[]{0x01, 0x04, 0x01, 0x05, 0x00, 0x00, 0x00, 0x00};
                short[] edit6 = new short[]{0x01, 0x05, 0x01, 0x05, 0x00, 0x00, 0x00, 0x00};

                short[] date = new short[]{0x02, 0x04, 0x15, 0x0B, 0x01, 0x15, 0x0B, 0x1E};

                short[] switches = new short[]{0x06, 0x00, 0x00, 0x0B, 0x00, 0x00, 0x00, 0x01};

                short[] timeOpen = new short[]{0x05, 0x01, 0x01, 0x0A, 0x0A, 0x00, 0x00, 0x00};
                short[] timeOpen2 = new short[]{0x05, 0x01, 0x01, 0x0A, 0x0C, 0x00, 0x00, 0x00};
                short[] timeStrategyOpen = unionShortArray(new short[]{0x40}, srcSubnetDeviceStrategy, sourceDeviceType, opCodeStrategy, targetSubNetDevice, logicTable1, logicOr, commonPre, timeOpen, timeOpen2, commonPad, commonPad);
                byte[] timeStrategyOpenCrc = buildCrc(timeStrategyOpen);
                final byte[] tsOpen = unionByteArray(transformShortToByteArray(unionShortArray(boot, timeStrategyOpen)), timeStrategyOpenCrc);
                sendCommand(socketChannel, tsOpen);
                Thread.sleep(500);
                short[] dateStrategy = unionShortArray(new short[]{0x40}, srcSubnetDeviceStrategy, sourceDeviceType, opCodeStrategy, targetSubNetDevice, logicTable2, logicAnd, commonPre, edit2, date, commonPad, commonPad);
                byte[] dateStrategyCrc = buildCrc(dateStrategy);
                byte[] ds = unionByteArray(transformShortToByteArray(unionShortArray(boot, dateStrategy)), dateStrategyCrc);
//                    sendCommand(socketChannel, ds);
//                    Thread.sleep(500);
                short[] switchStrategy = unionShortArray(new short[]{0x40}, srcSubnetDeviceStrategy, sourceDeviceType, opCodeStrategy, targetSubNetDevice, logicTable3, logicAnd, commonPre, edit3, switches, commonPad, commonPad);
                byte[] switchStrategyCrc = buildCrc(switchStrategy);
                byte[] ss = unionByteArray(transformShortToByteArray(unionShortArray(boot, switchStrategy)), switchStrategyCrc);
//                    sendCommand(socketChannel, ss);
//                    Thread.sleep(1000);

                // 设置逻辑表开受控目标
                for (int i = 0; i < 12; i++) {
                    short[] device = new short[]{0x04};
                    short[] loop = new short[]{(short) (i + 1)};
                    if (i > 3) {
                        device[0] = 0x05;
                        loop[0] = (short) (i - 3);
                    }
                    short[] setLogicInfo = unionShortArray(new short[]{0x15}, srcSubnetDeviceStrategy, sourceDeviceType, new short[]{0xDA, 0x1E, 0x01, 0x01}, new short[]{0x00, 0x17}, new short[]{(short) (i + 1)}, new short[]{0x04, 0x01}, device, loop, new short[]{0x64, 0x00, 0x00});
                    byte[] setLogicInfoCrc = buildCrc(setLogicInfo);
//                        sendCommand(socketChannel, unionByteArray(transformShortToByteArray(unionShortArray(boot, setLogicInfo)), setLogicInfoCrc));
//                        Thread.sleep(200);
                }

                short[] timeClose = new short[]{0x05, 0x01, 0x01, 0x0A, 0x0B, 0x00, 0x00, 0x00};
                short[] timeClose2 = new short[]{0x05, 0x01, 0x01, 0x0A, 0x0D, 0x00, 0x00, 0x00};
                short[] timeStrategyClose = unionShortArray(new short[]{0x40}, srcSubnetDeviceStrategy, sourceDeviceType, opCodeStrategy, targetSubNetDevice, logicTable4, logicOr, commonPre, timeClose, timeClose2, commonPad, commonPad);
                byte[] timeStrategyCloseCrc = buildCrc(timeStrategyClose);
                final byte[] tsClose = unionByteArray(transformShortToByteArray(unionShortArray(boot, timeStrategyClose)), timeStrategyCloseCrc);
                sendCommand(socketChannel, tsClose);
                Thread.sleep(200);

                dateStrategy = unionShortArray(new short[]{0x40}, srcSubnetDeviceStrategy, sourceDeviceType, opCodeStrategy, targetSubNetDevice, logicTable5, logicAnd, commonPre, edit5, date, commonPad, commonPad);
                dateStrategyCrc = buildCrc(dateStrategy);
                ds = unionByteArray(transformShortToByteArray(unionShortArray(boot, dateStrategy)), dateStrategyCrc);
//                    sendCommand(socketChannel, ds);
//                    Thread.sleep(500);

                switchStrategy = unionShortArray(new short[]{0x40}, srcSubnetDeviceStrategy, sourceDeviceType, opCodeStrategy, targetSubNetDevice, logicTable6, logicAnd, commonPre, edit6, switches, commonPad, commonPad);
                switchStrategyCrc = buildCrc(switchStrategy);
                ss = unionByteArray(transformShortToByteArray(unionShortArray(boot, switchStrategy)), switchStrategyCrc);
//                    sendCommand(socketChannel, ss);
//                    Thread.sleep(1000);

                for (int i = 0; i < 12; i++) {
                    short[] device = new short[]{0x04};
                    short[] loop = new short[]{(short) (i + 1)};
                    if (i > 3) {
                        device[0] = 0x05;
                        loop[0] = (short) (i - 3);
                    }
                    short[] setLogicInfo = unionShortArray(new short[]{0x15}, srcSubnetDeviceStrategy, sourceDeviceType, new short[]{0xDA, 0x1E, 0x01, 0x01}, new short[]{0x00, 0x1A}, new short[]{(short) (i + 1)}, new short[]{0x04, 0x01}, device, loop, new short[]{0x00, 0x00, 0x00});
                    byte[] setLogicInfoCrc = buildCrc(setLogicInfo);
//                        sendCommand(socketChannel, unionByteArray(transformShortToByteArray(unionShortArray(boot, setLogicInfo)), setLogicInfoCrc));
//                        Thread.sleep(200);
                }

                short[] strategyActive = new short[]{0x0D, 0xFD, 0xFD, 0xE2, 0xAA, 0xF1, 0x16, 0x01, 0x01, 0x01, 0xFF};
                byte[] strategyActiveCrc = buildCrc(strategyActive);
//                sendCommand(socketChannel, unionByteArray(transformShortToByteArray(unionShortArray(boot, strategyActive)), strategyActiveCrc));
//                Thread.sleep(1000);
                short[] strategyClose = new short[]{0x0D, 0xFD, 0xFD, 0xE2, 0xAA, 0xE0, 0x1C, 0x01, 0x01, 0x0B, 0x00};
                byte[] strategyCloseCrc = buildCrc(strategyClose);
//                sendCommand(socketChannel, unionByteArray(transformShortToByteArray(unionShortArray(boot, strategyClose)), strategyCloseCrc));
//                Thread.sleep(2000);

                for (int i = 0; i < 6; i++) {
                    short[] readLogicInfo = unionShortArray(new short[]{0x0E}, srcSubnetDeviceStrategy, sourceDeviceType, new short[]{0xDA, 0x18, 0x01, 0x01}, new short[]{0x00, 0x02}, new short[]{(short) (i + 1)});
                    byte[] readLogicInfoCrc = buildCrc(readLogicInfo);
//                        sendCommand(socketChannel, unionByteArray(transformShortToByteArray(unionShortArray(boot, readLogicInfo)), readLogicInfoCrc));
//                        Thread.sleep(200);
                }

                // 设置逻辑表受控目标
//                    for (int i = 0; i < 20; i++) {
//                        short[] setLogicInfo = unionShortArray(new short[]{0x15}, srcSubnetDeviceStrategy, sourceDeviceType, new short[]{0xDA, 0x1E, 0x01, 0x01}, new short[]{0x00, 0x16}, new short[]{(short) (i + 1)}, new short[]{0x03, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00});
//                        byte[] setLogicInfoCrc = buildCrc(setLogicInfo);
//                        sendCommand(socketChannel, unionByteArray(transformShortToByteArray(unionShortArray(boot, setLogicInfo)), setLogicInfoCrc));
//                        Thread.sleep(200);
//                        setLogicInfo = unionShortArray(new short[]{0x15}, srcSubnetDeviceStrategy, sourceDeviceType, new short[]{0xDA, 0x1E, 0x01, 0x01}, new short[]{0x00, 0x19}, new short[]{(short) (i + 1)}, new short[]{0x03, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00});
//                        setLogicInfoCrc = buildCrc(setLogicInfo);
//                        sendCommand(socketChannel, unionByteArray(transformShortToByteArray(unionShortArray(boot, setLogicInfo)), setLogicInfoCrc));
//                        Thread.sleep(200);
//                    }

                // 读取逻辑表受控目标
                for (int i = 0; i < 20; i++) {
                    short[] readLogicInfo = unionShortArray(new short[]{0x0E}, srcSubnetDeviceStrategy, sourceDeviceType, new short[]{0xDA, 0x1C, 0x01, 0x01}, new short[]{0x00, 0x17}, new short[]{(short) (i + 1)});
                    byte[] readLogicInfoCrc = buildCrc(readLogicInfo);
//                        sendCommand(socketChannel, unionByteArray(transformShortToByteArray(unionShortArray(boot, readLogicInfo)), readLogicInfoCrc));
//                        Thread.sleep(100);
                    readLogicInfo = unionShortArray(new short[]{0x0E}, srcSubnetDeviceStrategy, sourceDeviceType, new short[]{0xDA, 0x1C, 0x01, 0x01}, new short[]{0x00, 0x1A}, new short[]{(short) (i + 1)});
                    readLogicInfoCrc = buildCrc(readLogicInfo);
//                        sendCommand(socketChannel, unionByteArray(transformShortToByteArray(unionShortArray(boot, readLogicInfo)), readLogicInfoCrc));
//                        Thread.sleep(100);
                }

                first = false;
            }
        }
    }

    public static void main(String[] args) {
        final Server server = new Server();
        server.start();
//        Calendar calendar = Calendar.getInstance();
//        calendar.set(2021, Calendar.NOVEMBER, 13);
//        System.out.println(calendar.get(Calendar.DAY_OF_WEEK));
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

    private String getClientName(SocketChannel socketChannel) {
        return "客户端 [" + socketChannel.socket().getPort() + "]";
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

    private byte[] receive(SocketChannel socketChannel) throws IOException {
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

    public static short[] unionShortArray(short[]... args) {
        int length = 0;
        for (short[] a : args) {
            length += a.length;
        }
        short[] c = new short[length];
        int offset = 0;
        for (short[] a : args) {
            System.arraycopy(a, 0, c, offset, a.length);
            offset += a.length;

        }
        return c;
    }

    /**
     * short[] 转 byte[]
     *
     * @param iarr
     * @return
     */
    public static byte[] transformShortToByteArray(short[] iarr) {
        byte[] by = new byte[iarr.length];
        for (int i = 0; i < iarr.length; i++) {
            by[i] = (byte) iarr[i];
        }
        return by;
    }

    /**
     * 计算 crc
     *
     * @param arr
     * @return
     */
    public static byte[] buildCrc(short[] arr) {
        byte[] s = new byte[2];
        final long l = crc.calculateCRC(transformShortToByteArray(arr));
        s[0] = (byte) ((l >> 8) & 0xFF);
        s[1] = (byte) (l & 0xFF);
        return s;
    }
}