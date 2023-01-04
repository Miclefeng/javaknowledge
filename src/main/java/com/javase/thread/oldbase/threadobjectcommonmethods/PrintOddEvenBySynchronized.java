package com.javase.thread.oldbase.threadobjectcommonmethods;

public class PrintOddEvenBySynchronized {

    private static int count = 0;
    private static final Object lock = new Object();

    public static void main(String[] args) {

        new Thread(() -> {
            while (count < 100) {
                synchronized (lock) {
                    if ((count & 1) == 0) {
                        System.out.println(Thread.currentThread().getName() + " : " + count++);
                    }
                }
            }
        }, "偶数").start();

        new Thread(() -> {
            while (count < 100) {
                synchronized (lock) {
                    if ((count & 1) == 1) {
                        System.out.println(Thread.currentThread().getName() + " : " + count++);
                    }
                }
            }
        }, "奇数").start();
    }
}
