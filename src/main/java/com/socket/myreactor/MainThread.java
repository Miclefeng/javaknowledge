package com.socket.myreactor;

/**
 * @Description: some desc
 * @Author: miclefengzss
 * @Date: 2023/4/17 10:04
 */
public class MainThread {

    public static void main(String[] args) {

        SelectorThreadGroup boss = new SelectorThreadGroup(1);

        SelectorThreadGroup worker = new SelectorThreadGroup(3);

        boss.setWorker(worker);

        boss.bind(9999);
    }
}
