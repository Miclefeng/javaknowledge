package com.threadcoreknowledge.practice.blockingqueue;

import java.util.LinkedList;

/**
 * @author miclefengzss
 * 2021/12/13 上午10:00
 */
public class SynchronizedBlockingQueue<T> {

    private LinkedList<T> list = new LinkedList<>();

    private static final int MAX = 10;

    private int count;

    private synchronized void put(T t) {
        while (list.size() >= MAX) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        list.add(t);
        count++;
        System.out.println(Thread.currentThread().getName() + " -> list add: " + t + ", size: " + list.size());
        this.notifyAll();
    }

    private synchronized T take() {
        T t;
        while (list.size() == 0) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        t = list.poll();
        count--;
        this.notifyAll();
        System.out.println(Thread.currentThread().getName() + " -> list take: " + t + ", size: " + list.size());
        return t;
    }

    public static void main(String[] args) {
        SynchronizedBlockingQueue<Integer> blockingQueue = new SynchronizedBlockingQueue<>();

        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                for (int j = 1; j <= 10; j++) {
                    blockingQueue.put(j);
                }
            }, "P").start();
        }

        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                for (int j = 0; j < 5; j++) {
                    blockingQueue.take();
                }
            }, "C").start();
        }
    }
}
