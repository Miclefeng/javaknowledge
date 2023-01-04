package com.javase.thread.oldbase.juc.readwritelock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author miclefengzss
 * 2021/12/6 下午6:09
 */
public class NonFairReadWriteLock {

    public static ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    public static Lock readLock = readWriteLock.readLock();
    public static Lock writeLock = readWriteLock.writeLock();

    /**
     * 读锁插队策略
     * 公平锁：  不允许插队
     * 非公平锁：写锁可以随时插队
     * 读锁仅在等待队列头节点不是想获取写锁的线程的时候可以插队
     * <p>
     * 只能降级(写锁中获取读锁)不能升级(读锁中获取写锁)
     *
     * @param args
     */
    public static void main(String[] args) {
        new Thread(() -> write(writeLock), "T-1").start();
        new Thread(() -> read(readLock), "T-2").start();
        new Thread(() -> read(readLock), "T-3").start();
        new Thread(() -> write(writeLock), "T-4").start();
        new Thread(() -> read(readLock), "T-5").start();

        new Thread(() -> {
            Thread[] threads = new Thread[1000];
            for (int i = 0; i < threads.length; i++) {
                threads[i] = new Thread(() -> read(readLock), "子线程 S-T-" + i);
            }
            for (int i = 0; i < threads.length; i++) {
                threads[i].start();
            }
        }).start();
    }

    public static void read(Lock lock) {
        String name = Thread.currentThread().getName();
        System.out.println(name + " 开始尝试获取 R 读锁");
        lock.lock();
        try {
            System.out.println(name + " 获取到了 R 读锁");
            Thread.sleep(20);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println(name + " 释放了 R 读锁");
            lock.unlock();
        }
    }

    public static void write(Lock lock) {
        String name = Thread.currentThread().getName();
        System.out.println(name + " 开始尝试获取 W 写锁");
        lock.lock();
        try {
            System.out.println(name + " 获取到了 W 写锁");
            Thread.sleep(40);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println(name + " 释放了 W 写锁");
            lock.unlock();
        }
    }
}
