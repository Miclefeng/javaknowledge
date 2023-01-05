package com.javase.thread.base.question;

import java.util.concurrent.CountDownLatch;

/**
 * @author miclefengzss
 * 2023/1/5 下午3:54
 */
public class SequentialOutputCountDownLatch {

    static char cs = 'A';
    static int is = 1;
    static int ics = cs;

    public static void main(String[] args) {
        CountDownLatch latch1 = new CountDownLatch(1);
        CountDownLatch latch2 = new CountDownLatch(1);

        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 26; i++) {
                System.out.println("t1: " + cs);
                ics++;
                cs = (char) ics;
                latch2.countDown();
                try {
                    latch1.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 26; i++) {
                try {
                    latch2.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("t2: " + is);
                is++;
                latch1.countDown();
            }
        });

        t2.start();
        t1.start();
    }
}
