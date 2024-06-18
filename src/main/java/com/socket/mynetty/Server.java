package com.socket.mynetty;

/**
 * @Description: some desc
 * @Author: miclefengzss
 * @Date: 2023/11/7 20:57
 */
public class Server {

    public static void main(String[] args) {
        SelectorThreadGroup boss = new SelectorThreadGroup(1);
        SelectorThreadGroup worker = new SelectorThreadGroup(3);

        boss.setWorker(worker);
        boss.bind(9090);
    }
}
