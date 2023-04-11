package com.javase.io.bio.chatroom.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author miclefengzss
 * 2021/9/26 上午10:56
 */
public class UserInputHandler implements Runnable {

    private ChatClient chatClient;

    public UserInputHandler(ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    @Override
    public void run() {
        // 创建IO流
        BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            try {
                // 读取用户输入
                final String message = consoleReader.readLine();

                // 向服务端发送数据
                chatClient.send(message + "\n");

                // 判断客户端是否退出
                if (chatClient.readyToQuit(message)) {
                    break;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
