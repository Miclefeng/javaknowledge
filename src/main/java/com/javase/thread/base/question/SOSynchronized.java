package com.javase.thread.base.question;

/**
 * @Description: some desc
 * @Author: miclefengzss
 * @Date: 2023/11/17 16:13
 */
public class SOSynchronized {

    private static char cs = 'A';

    private static int is = 1;


    public static void main(String[] args) {
        SOSynchronized sos = new SOSynchronized();
        Object lock = new Object();

        Thread t1 = new Thread(() -> {
            synchronized (lock) {
                for (int i = 0; i < 26; i++) {
                    System.out.print(cs);
                    cs++;
                    lock.notify();
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
//                lock.notify();
            }
        }, "T1");

        Thread t2 = new Thread(() -> {
            synchronized (lock) {
                for (int i = 0; i < 26; i++) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    System.out.print(is + " ");
                    is++;
                    lock.notify();
                }
//                lock.notify();
            }
        }, "T2");

        // 必须保证 t2 先于 t1 执行。
        t2.start();
        t1.start();
    }
}
