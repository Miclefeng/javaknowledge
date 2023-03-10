package com.javase.thread.base;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author miclefengzss
 * 2023/2/26 下午9:38
 */
public class ReentrantLockReadWriteLock {

    static ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    static Lock readLock = readWriteLock.readLock();

    static Lock writeLock = readWriteLock.writeLock();

    static A cacheHold;

    public static void main(String[] args) {
        A hold = new A();
        cacheHold = hold;

        hold.count++;

        System.out.println(cacheHold.count);
    }


    static class A {
        public int count;
    }

}
