package com.javase.thread.base;

import java.util.concurrent.CountDownLatch;

/**
 * @author miclefengzss
 * 2022/12/27 下午1:33
 */
public class Orderliness {

    private static int x = 0, y = 0;
    private static int a = 0, b = 0;

    public static void main(String[] args) throws InterruptedException {

        long start = System.currentTimeMillis();
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            x = 0;
            y = 0;
            a = 0;
            b = 0;

            CountDownLatch countDownLatch = new CountDownLatch(2);

            Thread t1 = new Thread(() -> {
                a = 1;
                x = b;
                countDownLatch.countDown();
            });

            Thread t2 = new Thread(() -> {
                b = 1;
                y = a;
                countDownLatch.countDown();
            });

            t1.start();
            t2.start();

//            t1.join();
//            t2.join();
            countDownLatch.await();
            System.out.println(i);
            if (x == 0 && y == 0) {
                System.out.println("第 " + i + " 次, x = " + x + ", y = " + y);
                break;
            }
        }

        System.out.println("used millis time: " + (System.currentTimeMillis() - start));
    }
}
