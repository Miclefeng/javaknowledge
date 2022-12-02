package com.javase.thread.base.juc.readwritelock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author miclefengzss
 * 2021/12/6 下午6:09
 */
public class ThreadReadWriteLock {

    public static ReentrantLock reentrantLock = new ReentrantLock();

    public static ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    public static Lock readLock = readWriteLock.readLock();
    public static Lock writeLock = readWriteLock.writeLock();

    private static int value = 1;

    /**
     * 读锁插队策略
     * 公平锁：  不允许插队
     * 非公平锁：写锁可以随时插队
     *          读锁仅在等待队列头节点不是想获取写锁的线程的时候可以插队
     * @param args
     */
    public static void main(String[] args) {

        for (int i = 0; i < 4; i++) {
            new Thread(() -> read(readLock), "R-" + i).start();
        }

        for (int i = 0; i < 2; i++) {
            int finalI = i + 1;
            new Thread(() -> {
                try {
                    write(writeLock, finalI);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }, "W-" + i).start();
        }
    }

    public static void read(Lock lock) {
        String name = Thread.currentThread().getName();
        lock.lock();
        try {
            System.out.println(name + " 获取到了读锁，" + value + " : read over!");
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
            System.out.println(name + " 释放了读锁");
        }
    }

    public static void write(Lock lock, int v) throws InterruptedException {
        String name = Thread.currentThread().getName();
        lock.lock();
        try {
            System.out.println(name + " 获取到了写锁，" + v + " : write over!");
            value = v;
            Thread.sleep(1000);
        } finally {
            lock.unlock();
            System.out.println(name + " 释放了写锁");
        }
    }
}
