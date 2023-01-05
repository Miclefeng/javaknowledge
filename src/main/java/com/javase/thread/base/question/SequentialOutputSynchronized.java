package com.javase.thread.base.question;

/**
 * @author miclefengzss
 * 2023/1/5 下午3:54
 */
public class SequentialOutputSynchronized {

    static char cs = 'A';
    static int is = 1;
    static int ics = cs;

    public static void main(String[] args) {
        Object lock = new Object();

        Thread t1 = new Thread(() -> {
            synchronized (lock) {
                for (int i = 0; i < 26; i++) {
                    System.out.print(cs);
                    ics++;
                    cs = (char) ics;
                    lock.notify();
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        Thread t2 = new Thread(() -> {
            synchronized (lock) {
                for (int i = 0; i < 26; i++) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.print(is + " ");
                    is++;
                    lock.notify();
                }
            }
        });

        t2.start();
        t1.start();
    }
}
