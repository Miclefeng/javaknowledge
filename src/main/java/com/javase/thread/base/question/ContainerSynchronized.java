package com.javase.thread.base.question;

import java.util.LinkedList;

/**
 * @author miclefengzss
 * 2023/1/5 下午9:35
 */
public class ContainerSynchronized<T> {

    final private LinkedList<T> list = new LinkedList<T>();

    private static int MAX = 10;

    public synchronized void put(T t) {
        while (list.size() >= MAX) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        list.add(t);
        System.out.println(Thread.currentThread().getName() + " -> list add: " + t + ", size: " + list.size());
        this.notifyAll();
    }

    public synchronized T take() {
        T t;
        while (list.size() == 0) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        t = list.poll();
        System.out.println(Thread.currentThread().getName() + " -> list take: " + t + ", size: " + list.size());
        this.notifyAll();
        return t;
    }

    public static void main(String[] args) {
        ContainerSynchronized<Integer> cs = new ContainerSynchronized<>();

        for (int i = 0; i < 2; i++) {
            new Thread(() -> {
                for (int j = 0; j < 10; j++) {
                    cs.put(j);
                }
            }, "p-" + i).start();
        }

        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                for (int j = 0; j < 2; j++) {
                    cs.take();
                }
            }, "c-" + i).start();
        }
    }
}
