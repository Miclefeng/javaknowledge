package com.javase.io.socket;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author miclefengzss
 * 2021/9/25 下午3:51
 */
public class Server {

    public static final String QUIT = "quit";

    public static final Integer DEFAULT_PORT = 8888;

    public static ServerSocket serverSocket;

    public static void main(String[] args) {

        try {
            serverSocket = new ServerSocket(DEFAULT_PORT);
            System.out.println("启动服务器，监听端口: " + DEFAULT_PORT);

            while (true) {
                Socket accept = serverSocket.accept();
                System.out.println("客户端[" + accept.getPort() + "] 已连接.");
                // 接收数据IO
                BufferedReader reader = new BufferedReader(new InputStreamReader(accept.getInputStream()));
                // 发送数据IO
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(accept.getOutputStream()));

                // 读取客户端发送的消息
                String line;
                while ((line = reader.readLine()) != null) {
                    System.out.println("客户端[" + accept.getPort() + "]: " + line);

                    // 回复客户发送的消息
                    writer.write("服务器: " + line + "\n");
                    writer.flush();

                    // 查看客户端是否退出
                    if (QUIT.equals(line)) {
                        System.out.println("客户端["+accept.getPort()+"] 退出了.");
                        break;
                    }
                }
            }

        } catch (IOException e) {
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
}
