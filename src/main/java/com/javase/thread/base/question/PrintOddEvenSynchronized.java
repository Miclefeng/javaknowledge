package com.javase.thread.base.question;

/**
 * @author miclefengzss
 * 2023/1/8 下午10:35
 */
public class PrintOddEvenSynchronized {

    public static void main(String[] args) {
        Object lock = new Object();

        Thread t1 = new Thread(() -> {
            synchronized (lock) {
                for (int i = 0; i < 10; i +=2) {
                    System.out.println();
                }
            }
        }, "t1");

        Thread t2 = new Thread(() -> {

        }, "t2");

        t2.start();
        t1.start();
    }
}
