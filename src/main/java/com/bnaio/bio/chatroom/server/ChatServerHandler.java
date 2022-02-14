package com.bnaio.bio.chatroom.server;

import com.bnaio.foo.Hex;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * @author miclefengzss
 * 2021/9/26 上午10:00
 */
public class ChatServerHandler implements Runnable {

    private ChatServer chatServer;

    private Socket socket;

    public ChatServerHandler(ChatServer chatServer, Socket socket) {
        this.chatServer = chatServer;
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            chatServer.addClient(socket);
            int port = socket.getPort();

            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            while (true) {
                // 读取客户端发送的消息
                String message = reader.readLine();

                String forwardMessage = "客户端[" + port + "]: " + message;
                System.out.println(forwardMessage);

                System.err.println("客户端[" + port + "]: " + Hex.str2HexStr(message));

                // 转发给其他客户端消息
                chatServer.forwardMessage(socket, "");

                // 如果客户端退出，从已连接客户端表中移除客户端
                if (chatServer.readyToQuit(message)) {
                    chatServer.removeClient(socket);
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
