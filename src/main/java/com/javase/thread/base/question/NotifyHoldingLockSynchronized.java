package com.javase.thread.base.question;

import java.util.LinkedList;

/**
 *
 * @author miclefengzss
 * 2023/1/4 下午3:25
 */
public class NotifyHoldingLockSynchronized {

    LinkedList<Object> list = new LinkedList<>();

    public void add(Object o) {
        list.add(o);
    }

    public int size() {
        return list.size();
    }

    public static void main(String[] args) {
        NotifyHoldingLockSynchronized nhls = new NotifyHoldingLockSynchronized();

        Object lock = new Object();

        Thread t1 = new Thread(() -> {
            System.out.println("T1 线程启动。");
            synchronized (lock) {
                for (int i = 0; i < 10; i++) {
                    nhls.list.add(i);
                    System.out.println("T1 add " + i + ", size = " + nhls.size());
                    if (nhls.size() == 5) {
                        lock.notify();
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    millisSleep(500);
                }
            }
            System.out.println("T1 线程结束。");
        });

        Thread t2 = new Thread(() -> {
            System.out.println("T2 线程启动。");
            synchronized (lock) {
                if (nhls.size() != 5) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("T2 线程结束。");
                lock.notify();
            }
        });

        t2.start();
        t1.start();
    }

    static void millisSleep(long time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
