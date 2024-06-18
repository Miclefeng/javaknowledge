package com.javase.thread.base.question;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * @Description: some desc
 * @Author: miclefengzss
 * @Date: 2023/11/17 15:07
 */
public class NHLCountDownLatch02<T> {

    List<T> list = new LinkedList<>();

    public void add(T i) {
        list.add(i);
    }

    public int size() {
        return list.size();
    }

    public static void main(String[] args) {
        NHLCountDownLatch02<Integer> nhlc = new NHLCountDownLatch02<>();
        CountDownLatch latch1 = new CountDownLatch(1);
        CountDownLatch latch2 = new CountDownLatch(1);

        Thread t1 = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " start.");
            for (int i = 1; i <= 10; i++) {
                nhlc.add(i);
                System.out.println(Thread.currentThread().getName() + " : add " + i);
                if (nhlc.size() == 5) {
                    latch2.countDown();
                    System.out.println(Thread.currentThread().getName() + ": latch2 countDown.");
                    try {
                        latch1.await();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }, "T1");

        Thread t2 = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " start.");
            if (nhlc.size() != 5) {
                try {
                    Thread.sleep(1500);
                    System.out.println(Thread.currentThread().getName() + ": latch2 wait.");
                    latch2.await();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
            System.out.println(Thread.currentThread().getName() + " : end. =============");
            latch1.countDown();
        }, "T2");

        t1.start();
        t2.start();
    }
}
