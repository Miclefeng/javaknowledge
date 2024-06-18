package com.javase.thread.base.question;

import java.util.LinkedList;

/**
 * @Description: some desc
 * @Author: miclefengzss
 * @Date: 2023/11/17 15:08
 */
public class NHLSynchronized02 {

     LinkedList<Object> list = new LinkedList<>();

    public void add(Object o) {
        list.add(o);
    }

    public int size() {
        return list.size();
    }

    public static void main(String[] args) {

        NHLSynchronized02 nhls = new NHLSynchronized02();

        Object lock = new Object();

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + " : start.");
                synchronized (lock) {
                    for (int i = 1; i <= 10; i++) {
                        nhls.add(i);
                        if (nhls.size() == 5) {
                            lock.notify();
                            System.out.println(Thread.currentThread().getName() + " : notify.");
                            try {
                                lock.wait();
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                        }
                        System.out.println(Thread.currentThread().getName() + " : add " + i);
                    }
                }
            }
        }, "T1");

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + " : start.");
                synchronized (lock) { // 因为争抢同一把锁，t2一直获取不到锁，只有t1释放才能执行后续代码
                    // t1的notify() 先于 t2的wait() 执行，导致t2一直处于等待被唤醒的状态。
                    if (nhls.size() != 5) {
                        try {
                            System.out.println(Thread.currentThread().getName() + " : wait.");
                            lock.wait();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    System.out.println(Thread.currentThread().getName() + " : end.");
                    lock.notify();

                }
            }
        }, "T2");

        t1.start();
        t2.start();
    }
}
