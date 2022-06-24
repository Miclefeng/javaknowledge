package com.thread.base.practice.blockingqueue;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author miclefengzss
 * 2021/12/13 上午10:25
 */
public class ConditionBlockingQueue<T> {

    private LinkedList<T> list = new LinkedList<>();

    private static final int MAX = 10;

    private int count;

    private ReentrantLock lock = new ReentrantLock();

    private Condition consumer = lock.newCondition();

    private Condition producer = lock.newCondition();

    public void put(T t) {
        lock.lock();
        try {
            while (list.size() >= MAX) {
                producer.await();
            }
            list.add(t);
            count++;
            consumer.signalAll();
            System.out.println(Thread.currentThread().getName() + " -> List A: " + t + ", S: " + list.size());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public T take() {
        T t = null;
        lock.lock();
        try {
            while (list.size() == 0) {
                consumer.await();
            }
            t = list.poll();
            count--;
            System.out.println(Thread.currentThread().getName() + " -> List T: " + t + ", S: " + list.size());
            producer.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        return t;
    }

    public static void main(String[] args) {

        ConditionBlockingQueue<Integer> blockingQueue = new ConditionBlockingQueue<>();

        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                for (int j = 1; j <= 10; j++) {
                    blockingQueue.put(j);
                }
            }, "P").start();
        }

        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                for (int j = 1; j <= 5; j++) {
                    blockingQueue.take();
                }
            }, "C").start();
        }
    }
}
