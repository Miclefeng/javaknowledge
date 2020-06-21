package com.threadcoreknowledge.threadobjectcommonmethods;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * sleep不释放 lock (lock 需要手动释放)
 * sleep 方法可以让线程进入wait状态，并且不占用CPU资源，但是不释放锁，直达规定的时间
 * 后再执行，休眠期间如果被中断，会抛出异常并清除中断状态
 */
public class ThreadSleepDontReleaseLock implements Runnable {

    private static final Lock lock = new ReentrantLock();

    public static void main(String[] args) {
        ThreadSleepDontReleaseLock r = new ThreadSleepDontReleaseLock();
        new Thread(r).start();
        new Thread(r).start();
    }

    @Override
    public void run() {
        sync();
    }

    private synchronized void sync() {
        lock.lock();
        System.out.println(Thread.currentThread().getName() + " 获取到了lock.");
        try {
            Thread.sleep(2000);
            System.out.println(Thread.currentThread().getName() + " 已经唤醒.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        System.out.println(Thread.currentThread().getName() + " 释放了lock.");
    }
}
