package com.designpattern.pattern.behavioral.observer;

/**
 * @Description: some desc
 * @Author: miclefengzss
 * @Date: 2023/10/24 16:50
 */
public class Client {

    public static void main(String[] args) {
        LotteryService lotteryService = new LotteryServiceImpl();
        lotteryService.lotteryAndMessage("202023038203802382");
    }
}
