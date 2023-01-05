package com.javase.thread.base.question;

import java.util.LinkedList;
import java.util.concurrent.locks.LockSupport;

/**
 * @author miclefengzss
 * 2023/1/5 下午1:41
 */
public class NotifyHoldingLockLockSupport {

    LinkedList<Object> list = new LinkedList<>();

    public void add(Object o) {
        list.add(o);
    }

    public int size() {
        return list.size();
    }

    static Thread t1 = null, t2 = null;

    public static void main(String[] args) {
        NotifyHoldingLockLockSupport nhlls = new NotifyHoldingLockLockSupport();

        t1 = new Thread(() -> {
            System.out.println("t1 启动。");
            for (int i = 0; i < 10; i++) {
                nhlls.add(i);
                System.out.println("t1 add " + i + ", size = " + nhlls.size());
                if (nhlls.size() == 5) {
                    LockSupport.unpark(t2);
                    LockSupport.park();
                }
                millisSleep();
            }
            System.out.println("t1 结束。");
        });

        t2 = new Thread(() -> {
            System.out.println("t2 启动。");
            LockSupport.park();
            LockSupport.unpark(t1);
            System.out.println("t2 结束。");
            millisSleep();
        });

        t2.start();
        t1.start();
    }

    static void millisSleep() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
