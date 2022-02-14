package com.bnaio.bio.chatroom.server;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author miclefengzss
 * 2021/9/26 上午9:42
 */
public class ChatServer {

    public static final int DEFAULT_PORT = 8888;

    public static final String QUIT = "quit";

    public ServerSocket serverSocket;

    public ExecutorService executorService;

    public Map<Integer, Writer> connectionClients;

    public ChatServer() {
        executorService = Executors.newFixedThreadPool(10);
        connectionClients = new HashMap<>();
    }

    public synchronized void addClient(Socket socket) throws IOException {
        if (socket != null) {
            int port = socket.getPort();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            connectionClients.put(port, writer);
            System.out.println("客户端[" + port + "] 已连接到服务器.");
        }
    }

    public synchronized void removeClient(Socket socket) throws IOException {
        if (socket != null) {
            int port = socket.getPort();
            if (connectionClients.containsKey(port)) {
                connectionClients.get(port).close();
            }
            connectionClients.remove(port);
            System.out.println("客户端[" + port + "] 已断开连接.");
        }
    }

    public synchronized void forwardMessage(Socket socket, String message) throws IOException {
        for (int clientId : connectionClients.keySet()) {
            if (clientId != socket.getPort()) {
                Writer writer = connectionClients.get(clientId);
                writer.write(message + "\n");
                writer.flush();
            }
        }
    }

    public boolean readyToQuit(String msg) {
        return QUIT.equals(msg);
    }

    public synchronized void close() {
        try {
            if (serverSocket != null) {
                serverSocket.close();
            }
            System.out.println("关闭serverSocket");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void start() {
        try {
            // 绑定监听端口
            serverSocket = new ServerSocket(DEFAULT_PORT);

            while (true) {
                // 等待客户端连接
                Socket socket = serverSocket.accept();

                // 处理客户端信息
                executorService.execute(new ChatServerHandler(this, socket));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            close();
        }
    }

    public static void main(String[] args) {
        final ChatServer chatServer = new ChatServer();
        chatServer.start();
    }
}
