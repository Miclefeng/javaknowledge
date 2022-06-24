package com.thread.base.juc.interview.problem01;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.LockSupport;

/**
 * 两个线程执行，1个线程往队列中添加10个元素，当队列的长度为5时，第二个线程开始执行，第二个线程执行完后第一个线程在继续执行
 *
 * @author miclefengzss
 * 2021/12/7 下午3:36
 */
public class T04_LockSupport {
    List<Integer> list = new LinkedList<>();

    public void add(Integer i) {
        list.add(i);
    }

    public int size() {
        return list.size();
    }

    static Thread t1, t2;

    public static void main(String[] args) {

        T04_LockSupport t04 = new T04_LockSupport();

        t1 = new Thread(() -> {
            System.out.println("T1 start.");
            for (int i = 0; i < 10; i++) {
                t04.add(i);
                System.out.println("T1 add " + i + ",list size: " + t04.size());
                if (t04.size() == 5) {
                    LockSupport.unpark(t2);

                    LockSupport.park();
                }
            }
            System.out.println("T1 end.");
        });

        t2 = new Thread(() -> {
            System.out.println("T2 start.");
            LockSupport.park();

            LockSupport.unpark(t1);
            System.out.println("T2 end.");
        });

        t1.start();
        t2.start();
    }
}
