package com.javase.thread.base.question;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author miclefengzss
 * 2023/1/6 上午10:31
 */
public class ContainerReentrantLockCondition<T> {

    LinkedList<T> list = new LinkedList<>();

    static int MAX = 10;

    ReentrantLock lock = new ReentrantLock();

    Condition producer = lock.newCondition();
    Condition consumer = lock.newCondition();

    public void put(T t) {
        lock.lock();
        try {
            while (list.size() == MAX) {
                producer.await();
            }
            list.add(t);
            System.out.println(Thread.currentThread().getName() + " -> list add: " + t + ", size: " + list.size());
            consumer.signalAll();
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
            System.out.println(Thread.currentThread().getName() + " -> list take: " + t + ", size: " + list.size());
            producer.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

        return t;
    }

    public static void main(String[] args) {
        ContainerReentrantLockCondition<Integer> cc = new ContainerReentrantLockCondition<>();

        for (int i = 0; i < 2; i++) {
            new Thread(() -> {
                for (int j = 0; j < 10; j++) {
                    cc.put(j);
                }
            }, "p" + i).start();
        }

        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                for (int j = 0; j < 4; j++) {
                    cc.take();
                }
            }, "c" + i).start();
        }
    }
}
