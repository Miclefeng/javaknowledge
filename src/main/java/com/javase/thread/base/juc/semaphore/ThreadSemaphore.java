package com.javase.thread.base.juc.semaphore;

import java.util.concurrent.Semaphore;

/**
 * @author miclefengzss
 * 2021/12/7 上午11:09
 */
public class ThreadSemaphore {

    public static void main(String[] args) {
        Semaphore s = new Semaphore(2);

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                String name = Thread.currentThread().getName();
                try {
                    s.acquire();
                    System.out.println(name + " start  running...");
                    Thread.sleep(200);
                    System.out.println(name + " end    running...");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    s.release();
                }
            }
        };

        new Thread(runnable).start();
        new Thread(runnable).start();
        new Thread(runnable).start();
        new Thread(runnable).start();
    }
}
