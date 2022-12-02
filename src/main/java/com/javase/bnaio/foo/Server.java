package com.javase.bnaio.foo;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;

/**
 * @author miclefengzss
 * 2021/9/25 下午3:51
 */
public class Server {

    public static final Integer DEFAULT_PORT = 50880;

    public static ServerSocket serverSocket;

    public static void main(String[] args) {

        try {
            serverSocket = new ServerSocket(DEFAULT_PORT);
            System.out.println("启动服务器，监听端口: " + DEFAULT_PORT);





            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("客户端[" + socket.getPort() + "] 已连接.");
                // 接收数据IO
                final InputStream inputStream = socket.getInputStream();
                // 发送数据IO
                final OutputStream outputStream = socket.getOutputStream();

                // 读取客户端发送的消息
                byte[] buffer = new byte[1024];
                while ((inputStream.read(buffer)) != -1) {
                    System.out.println("客户端[" + socket.getPort() + "]: " + Arrays.toString(ByteArrayUtil.get(buffer, 0, buffer.length)));
                    final String s = Hex.byte2HexStr(buffer);
                    System.out.println("客户端[" + socket.getPort() + "]: " + s);
                    // 回复客户发送的消息
//                    String cmd = "0F0000E2AA00310104016400005B78";

//                    final byte[] a = transformIntToByteArray(new int[]{0xAA, 0xAA});
                    final byte[] open = transformIntToByteArray(new int[]{0xAA, 0xAA, 0x0F, 0x00, 0x00, 0xE2, 0xAA, 0x00, 0x31, 0x01, 0x04, 0x01, 0x64, 0x00, 0x00, 0x5B, 0x78});

                    final byte[] shutdown = transformIntToByteArray(new int[]{0xAA, 0xAA, 0x0F, 0x00, 0x00, 0xE2, 0xAA, 0x00, 0x31, 0x01, 0x04, 0x01, 0x00, 0x00, 0x00, 0x1C, 0xD3});

                    System.out.println(Hex.byte2HexStr(open));
                    System.out.println(Hex.byte2HexStr(shutdown));
                    outputStream.write(open);
                    outputStream.flush();
                    Thread.sleep(5000);
                    outputStream.write(shutdown);
                    outputStream.flush();
                }
            }

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        } finally {
            try {
                if (serverSocket != null) {
                    serverSocket.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    private static byte[] unionByteArray(byte[] a, byte[] b) {
        byte[] c = new byte[a.length + b.length];
        System.arraycopy(a, 0, c, 0, a.length);
        System.arraycopy(b, 0, c, a.length, b.length);
        return c;
    }

    /**
     * int[] 转 byte[]
     *
     * @param iarr
     * @return
     */
    private static byte[] transformIntToByteArray(int[] iarr) {
        byte[] by = new byte[iarr.length];
        for (int i = 0; i < iarr.length; i++) {
            by[i] = (byte) iarr[i];
        }
        return by;
    }
}
