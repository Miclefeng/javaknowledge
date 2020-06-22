package com.threadcoreknowledge.threadobjectcommonmethods;

import java.util.concurrent.TimeUnit;

public class ThreadSleepInterrupt implements Runnable {

    public static void main(String[] args) {
        ThreadSleepInterrupt r = new ThreadSleepInterrupt();
        Thread t1 = new Thread(r);
        t1.start();
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t1.interrupt();
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            try {
//                TimeUnit.HOURS.sleep(1);
//                TimeUnit.MINUTES.sleep(1);
                System.out.println(i);
                TimeUnit.SECONDS.sleep(1);
                TimeUnit.MILLISECONDS.sleep(1);
            } catch (InterruptedException e) {
//                e.printStackTrace();
                System.out.println(Thread.currentThread().getName() + " 被中断了.");
                break;
            }
        }
    }
}
