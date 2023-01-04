package com.javase.thread.oldbase.threadobjectcommonmethods;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * sleep不释放 lock (lock 需要手动释放)
 * sleep 方法可以让线程进入wait状态，并且不占用CPU资源，但是不释放锁，直达规定的时间
 * 后再执行，休眠期间如果被中断，会抛出异常并清除中断状态
 * <p>
 * wait 和 sleep方法的异同
 * 相同:
 * 1、wait和sleep都是可以使线程阻塞，对应的状态是waiting和time_waiting
 * 2、wait和sleep都是响应中断 Thread.interrupt()
 * 不同:
 * 1、wait方法必须在同步方法中执行，sleep不需要
 * 2、在同步方法中执行sleep不会释放monitor锁，但wait会释放monitor锁
 * 3、sleep休眠之后会主动退出阻塞，wait需要被其他线程中断后才能退出阻塞
 * 4、wait、notify、notifyAll 都是Object类的方法，sleep和yield是Thread的方法
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
