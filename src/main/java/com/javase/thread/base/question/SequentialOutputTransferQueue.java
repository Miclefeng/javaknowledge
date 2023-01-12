package com.javase.thread.base.question;

import java.util.concurrent.LinkedTransferQueue;

/**
 * @author miclefengzss
 * 2023/1/5 下午3:54
 */
public class SequentialOutputTransferQueue {

    static LinkedTransferQueue<Character> transferQueue = new LinkedTransferQueue<>();

    static char[] characters = "ABCDEFGHI".toCharArray();
    static char[] numbers = "123456789".toCharArray();

    public static void main(String[] args) {

        Thread t1 = new Thread(() -> {
                for (char c : characters) {
                    try {
                        transferQueue.transfer(c);
                        System.out.print(transferQueue.take());
                        System.out.print("\t");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
        }, "t1");

        Thread t2 = new Thread(() -> {
                for (char n : numbers) {
                    try {
                        System.out.print(transferQueue.take());
                        transferQueue.transfer(n);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
        }, "t2");

        t1.start();
        t2.start();
    }
}
