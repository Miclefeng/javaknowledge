package com.socket.io.bio.chatroom.client;


import java.io.*;
import java.net.Socket;

/**
 * @author miclefengzss
 * 2021/9/26 上午10:55
 */
public class ChatClient {

    public static final String DEFAULT_HOST = "127.0.0.1";

    public static final int DEFAULT_PORT = 8888;

    public static final String QUIT = "quit";

    public Socket socket;

    public BufferedReader reader;

    public BufferedWriter writer;

    public void send(String message) throws IOException {
        if (!socket.isOutputShutdown()) {
            writer.write(message);
            writer.flush();
        }
    }

    public String receive() throws IOException {
        String message = "";
        if (!socket.isInputShutdown()) {
            message = reader.readLine();
        }
        return message;
    }

    public boolean readyToQuit(String message) {
        return QUIT.equals(message);
    }

    public void close() {
        if (writer != null) {
            try {
                writer.close();
                System.out.println("客户端已关闭.");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void start() {
        try {
            // 创建socket
            socket = new Socket(DEFAULT_HOST, DEFAULT_PORT);

            // 创建IO流
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

            // 读取用户输入
            new Thread(new UserInputHandler(this)).start();

            // 打印服务器消息
            String message;
            while ((message = receive()) != null) {
                System.out.println(message);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            close();
        }
    }

    public static void main(String[] args) {
        final ChatClient chatClient = new ChatClient();
        chatClient.start();
    }
}
