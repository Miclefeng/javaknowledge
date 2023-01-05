package com.javase.thread.base.question;

import java.util.LinkedList;
import java.util.concurrent.CountDownLatch;

/**
 * @author miclefengzss
 * 2023/1/5 上午11:20
 */
public class NotifyHoldingLockCountDownLatch {

    LinkedList<Object> list = new LinkedList<>();

    public void add(Object o) {
        list.add(o);
    }

    public int size() {
        return list.size();
    }

    public static void main(String[] args) {
        NotifyHoldingLockCountDownLatch nhlc = new NotifyHoldingLockCountDownLatch();

        CountDownLatch latch1 = new CountDownLatch(1);
        CountDownLatch latch2 = new CountDownLatch(1);

        Thread t1 = new Thread(() -> {
            System.out.println("t1 启动。");
            for (int i = 0; i < 10; i++) {
                nhlc.add(i);
                System.out.println("t1 add " + i);
                if (nhlc.size() == 5) {
                    latch2.countDown();
                    try {
                        latch1.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                millisSleep();
            }
            System.out.println("t1 结束。");
        });

        Thread t2 = new Thread(() -> {
            System.out.println("t2 启动。");
            try {
                latch2.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("t2 结束。");
            latch1.countDown();
            millisSleep();
        });

        t2.start();
        t1.start();
    }

    static void millisSleep() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
