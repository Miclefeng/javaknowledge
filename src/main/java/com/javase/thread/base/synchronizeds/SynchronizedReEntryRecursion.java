package com.javase.thread.base.synchronizeds;

/**
 * synchronized 修饰的同一个方法是可重入的
 * synchronized 的锁是线程级别的
 */
public class SynchronizedReEntryRecursion {

    int a = 0;

    public static void main(String[] args) {
        SynchronizedReEntryRecursion s = new SynchronizedReEntryRecursion();
        s.method();
    }

    public synchronized void method() {
        System.out.println("我是Method，a = " + a);
        if (a == 0) {
            a++;
            method();
        }
    }
}
