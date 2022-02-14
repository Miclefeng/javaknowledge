package com.threadcoreknowledge.juc.interview;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author miclefengzss
 * 2021/12/7 下午4:37
 */
public class MyContainerConditions<T> {

    public LinkedList<T> list = new LinkedList<>();

    public static final int MAX = 10;

    int count = 0;

    public ReentrantLock lock = new ReentrantLock();

    public Condition consumer = lock.newCondition();
    public Condition producer = lock.newCondition();

    public void put(T e) {
        lock.lock();
        try {
            while (list.size() >= MAX) {
                producer.await();
            }
            list.add(e);
            count++;
            consumer.signalAll();
            System.out.println(Thread.currentThread().getName() + " -> list add: " + e + ",list size: " + list.size());
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public T take() {
        T e = null;
        lock.lock();
        try {
            while (list.size() == 0) {
                consumer.await();
            }
            e = list.poll();
            count--;
            producer.signalAll();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        } finally {
            lock.unlock();
        }
        System.out.println(Thread.currentThread().getName() + " ->  take: " + e + ",list size: " + list.size());
        return e;
    }

    /**
     * 两个 condition 相当于将不同的线程放入不同的等待队列
     *
     * @param args
     */
    public static void main(String[] args) {
        final MyContainerConditions<Integer> container = new MyContainerConditions<>();

        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                for (int j = 0; j < 5; j++) {
                    container.take();
                }
            }, "c").start();
        }

        for (int i = 0; i < 2; i++) {
            new Thread(() -> {
                for (int j = 1; j <= 25; j++) {
                    container.put(j);
                }
            }, "p").start();
        }
    }
}
