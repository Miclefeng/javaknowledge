package com.javase.thread.base.container;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author miclefengzss
 * 2023/1/10 下午3:24
 */
public class ConcurrentLinkedQueueLearn {

    static ConcurrentLinkedQueue<String> tickets = new ConcurrentLinkedQueue<>();

    static AtomicInteger times = new AtomicInteger();

    static {
        for (int i = 1; i <= 1000; i++) {
            tickets.add("票编号: " + i);
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                while (true) {
                    String t = tickets.poll();
                    if (t == null) {
                        break;
                    }
                    times.incrementAndGet();
                    System.out.println(t);
                }
            }).start();
        }

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(times.get());
    }
}
