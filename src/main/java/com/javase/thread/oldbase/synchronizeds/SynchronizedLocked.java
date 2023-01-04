package com.javase.thread.oldbase.synchronizeds;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SynchronizedLocked {

    Lock lock = new ReentrantLock();

    public static void main(String[] args) {
        SynchronizedLocked s = new SynchronizedLocked();
        s.method1();
        s.method2();
    }

    public synchronized void method1() {
        System.out.println("synchronized 锁.");
    }

    public void method2() {
        lock.lock();
        try {
            System.out.println("lock 锁.");
        } finally {
            lock.unlock();
        }
    }
}
