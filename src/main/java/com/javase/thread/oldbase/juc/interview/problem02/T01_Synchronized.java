package com.javase.thread.oldbase.juc.interview.problem02;

import java.util.LinkedList;

/**
 * @author miclefengzss
 * 2021/12/7 下午4:21
 */
public class T01_Synchronized<T> {

    public LinkedList<T> list = new LinkedList<>();

    public static final int MAX = 10;

    int count = 0;

    public synchronized void put(T e) {
        while (list.size() >= MAX) {
            try {
                this.wait();
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }

        list.add(e);
        count++;
        System.out.println(Thread.currentThread().getName() + " -> list add: " + e + ",list size: " + list.size());
        this.notifyAll();
    }

    public synchronized T take() {
        while (list.size() == 0) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        T e = list.poll();
        count--;
        System.out.println(Thread.currentThread().getName() + " ->  take: " + e + ",list size: " + list.size());
        this.notifyAll();
        return e;
    }

    public static void main(String[] args) {
        final T01_Synchronized<Integer> objectT01 = new T01_Synchronized<>();

        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                for (int j = 0; j < 5; j++) {
                    objectT01.take();
                }
            }, "c").start();
        }

        for (int i = 0; i < 2; i++) {
            new Thread(() -> {
                for (int j = 1; j <= 25; j++) {
                    objectT01.put(j);
                }
            }, "p").start();
        }
    }
}
