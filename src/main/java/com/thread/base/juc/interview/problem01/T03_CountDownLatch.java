package com.thread.base.juc.interview.problem01;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * 两个线程执行，1个线程往队列中添加10个元素，当队列的长度为5时，第二个线程开始执行，第二个线程执行完后第一个线程在继续执行
 *
 * @author miclefengzss
 * 2021/12/7 下午3:18
 */
public class T03_CountDownLatch {

    List<Integer> list = new LinkedList<>();

    public void add(Integer i) {
        list.add(i);
    }

    public int size() {
        return list.size();
    }

    public static void main(String[] args) {

        final T03_CountDownLatch t03 = new T03_CountDownLatch();

        CountDownLatch c1 = new CountDownLatch(1);
        CountDownLatch c2 = new CountDownLatch(1);

        Thread t1 = new Thread(() -> {
            System.out.println("T1 start.");
            for (int i = 0; i < 10; i++) {
                t03.add(i);
                System.out.println("T1 add " + i + ", list size: " + t03.size());
                if (t03.size() == 5) {
                    c2.countDown();
                    try {
                        c1.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            System.out.println("T1 end.");
        });

        Thread t2 = new Thread(() -> {
            System.out.println("T2 start.");
            try {
                c2.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            c1.countDown();
            System.out.println("T2 end.");
        });

        t1.start();
        t2.start();
    }
}
