package com.javase.thread.base.question;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Description: some desc
 * @Author: miclefengzss
 * @Date: 2023/11/17 16:29
 */
public class SyncContainerReentrantLockCondition02<T> {

    static int MAX = 10;

    int count = 0;

    LinkedList<T> list = new LinkedList<>();

    ReentrantLock lock = new ReentrantLock();
    Condition producer = lock.newCondition();
    Condition consumer = lock.newCondition();

    public int size() {
        return list.size();
    }

    public void put(T v) {
        lock.lock();
        try {
            while (size() == MAX) {
                producer.await();
            }
            list.add(v);
            System.out.println(Thread.currentThread().getName() + " -> list add: " + v + ", size: " + list.size());
            count++;
            consumer.signalAll();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            lock.unlock();
        }
    }

    public T take() {
        T ans;
        lock.lock();
        try {
            while (size() == 0) {
                consumer.await();
            }
            ans = list.poll();
            count--;
            System.out.println(Thread.currentThread().getName() + " -> list take: " + ans + ", size: " + list.size());
            producer.signalAll();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            lock.unlock();
        }

        return ans;
    }

    public static void main(String[] args) {
        SyncContainerReentrantLockCondition02<Integer> syncContainer = new SyncContainerReentrantLockCondition02<>();

        for (int i = 0; i < 2; i++) {
            new Thread(() -> {
                for (int j = 0; j < 10; j++) {
                    syncContainer.put(j);
                }
            }, "p" + i).start();
        }

        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                for (int j = 0; j < 4; j++) {
                    syncContainer.take();
                }
            }, "c" + i).start();
        }
    }
}
