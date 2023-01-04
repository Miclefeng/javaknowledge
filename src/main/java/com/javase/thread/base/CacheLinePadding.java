package com.javase.thread.base;

import java.util.concurrent.CountDownLatch;

/**
 * @author miclefengzss
 * 2022/12/26 下午4:21
 */
public class CacheLinePadding {

    private static final long COUNT = 1_0000_0000L;

    private static T[] arr = new T[2];

    public static class T {
        private long p1, p2, p3, p4, p5, p6, p7;
        /**
         * volatile 保证多线程的可见性，禁止指令重排序
         * 缓存行(64字节大小，局部性原理)和CPU缓存一致性协议的存在导致 x 和后续字节被一起读入到 CPU 缓存中，为了保证多线程的可见性
         * 数据会在缓存之间来回同步，导致效率低下
         * 使用字节填充可以保证两个线程访问的数据在不同的缓存行，不用缓存同步
         */
        private volatile long x;

        private long p8, p9, p10, p11, p12, p13, p14;
    }

    static {
        arr[0] = new T();
        arr[1] = new T();
    }

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(2);

        Thread t1 = new Thread(() -> {
            for (long i = 0; i < COUNT; i++) {
                arr[0].x = i;
            }
            System.out.println("arr[0].x = " + arr[0].x);
            countDownLatch.countDown();
        });

        Thread t2 = new Thread(() -> {
            for (long i = 0; i < COUNT; i++) {
                arr[1].x = i;
            }
            System.out.println("arr[1].x = " + arr[1].x);
            countDownLatch.countDown();
        });

        long start = System.currentTimeMillis();
        t1.start();
        t2.start();
        countDownLatch.await();
        System.out.println("used second time: " + ((System.currentTimeMillis() - start)));
    }
}
