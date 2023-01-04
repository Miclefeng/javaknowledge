package com.javase.thread.oldbase.synchronizeds;

/**
 * synchronized 修饰的不同方式是可重入的
 * synchronized 的锁是线程级别的
 */
public class SynchronizedReEntryDifferentMethod {

    public static void main(String[] args) {
        SynchronizedReEntryDifferentMethod s = new SynchronizedReEntryDifferentMethod();
        s.method1();
    }

    public synchronized void method1() {
        System.out.println("我是Method 01.");
        method2();
    }

    private synchronized void method2() {
        System.out.println("我是Method 02.");
    }
}
