package com.javase.thread.base.question;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.LockSupport;

/**
 * @Description: some desc
 * @Author: miclefengzss
 * @Date: 2023/11/17 15:47
 */
public class NHLLockSupport02<T> {

    List<T> list = new LinkedList<>();

    public void add(T i) {
        list.add(i);
    }

    public int size() {
        return list.size();
    }

    static Thread t1, t2;

    public static void main(String[] args) {
        NHLLockSupport02<Integer> nhll = new NHLLockSupport02<>();

        t1 = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + ": start.");
            for (int i = 1; i <= 10; i++) {
                nhll.add(i);
                System.out.println(Thread.currentThread().getName()+": add " + i);
                if (nhll.size() == 5) {
                    LockSupport.unpark(t2);

                    LockSupport.park();
                }
            }
        }, "T1");

        t2 = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + ": start.");
            LockSupport.park();

            LockSupport.unpark(t1);
            System.out.println(Thread.currentThread().getName() + ": end.");
        }, "T2");

        t1.start();
        t2.start();
    }
}
