package com.javase.thread.oldbase.threadobjectcommonmethods;

import java.util.concurrent.TimeUnit;

/**
 * join 期间被中断
 */
public class ThreadJoinInterrupt {

    public static void main(String[] args) {
        Thread mainThread = Thread.currentThread();
        Thread t1 = new Thread(() -> {
            try {
                mainThread.interrupt();
                TimeUnit.SECONDS.sleep(2);
                System.out.println(Thread.currentThread().getName() + " 执行完毕.");
            } catch (InterruptedException e) {
                System.out.println(Thread.currentThread().getName() + " 执行中断了.");
            }
        });

        t1.start();
        System.out.println("子线程开始执行.");
        try {
            t1.join();
        } catch (InterruptedException e) {
            // 主线程执行中断 通知子线程，让子线程也响应中断
            System.out.println(mainThread.getName() + " 执行中断了.");
            t1.interrupt();
        }
        System.out.println("子线程执行完毕.");
    }
}
