package com.designpattern.pattern.creational.builder;

/**
 * @Description: some desc
 * @Author: miclefengzss
 * @Date: 2023/10/22 22:43
 */
public class App {

    public static void main(String[] args) {
        try {
            RabbitMQClient queue = new RabbitMQClient.Builder().setHost("192.168.1.11").setPort(5672).setMode(1).setQueue("queue").build();

            queue.sendMessage();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
