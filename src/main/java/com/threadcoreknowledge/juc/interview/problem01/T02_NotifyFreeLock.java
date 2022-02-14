package com.threadcoreknowledge.juc.interview.problem01;

import java.util.LinkedList;
import java.util.List;

/**
 * 两个线程执行，1个线程往队列中添加10个元素，当队列的长度为5时，第二个线程开始执行，第二个线程执行完后第一个线程在继续执行
 *
 * @author miclefengzss
 * 2021/12/7 下午2:52
 */
public class T02_NotifyFreeLock {

    List<Integer> list = new LinkedList<>();

    public void add(Integer i) {
        list.add(i);
    }

    public int size() {
        return list.size();
    }

    static Object lock = new Object();

    public static void main(String[] args) {

        final T02_NotifyFreeLock c = new T02_NotifyFreeLock();

        Thread t1 = new Thread(() -> {
            System.out.println("T1 start.");
            for (int i = 0; i < 10; i++) {
                synchronized (lock) {
                    c.add(i);
                    System.out.println("add " + i);
                    if (c.size() == 5) {
                        lock.notify();

                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
            System.out.println("T1 end.");
        });

        Thread t2 = new Thread(() -> {
            System.out.println("T2 start.");
            synchronized (lock) {
                if (c.size() != 5) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                lock.notify();
            }
            System.out.println("T2 end.");
        });

        t1.start();
        t2.start();
    }
}
