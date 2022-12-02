package com.javase.thread.base.threadobjectcommonmethods;

import java.util.concurrent.TimeUnit;

/**
 * join 原理
 */
public class ThreadJoinPrinciple {

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " 执行完毕.");
        });

        t1.start();
        System.out.println("开始执行子线程.");
//        t1.join();
        // 用 synchronized 可以实现 join 的替代写法
        synchronized (t1) {
            t1.wait();
        }
        System.out.println("子线程执行完毕.");
    }
}
