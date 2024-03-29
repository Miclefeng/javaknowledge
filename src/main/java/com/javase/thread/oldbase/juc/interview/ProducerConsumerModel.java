package com.javase.thread.oldbase.juc.interview;

import java.util.Date;
import java.util.LinkedList;

/**
 * 生产者消费者模式
 */
public class ProducerConsumerModel {

    public static void main(String[] args) {
        EventStorage storage = new EventStorage();
        Producer producer = new Producer(storage);
        Consumer consumer = new Consumer(storage);

        Thread p = new Thread(producer);
        Thread c = new Thread(consumer);

        p.start();
        c.start();
    }
}

class Producer implements Runnable {

    private final EventStorage storage;

    public Producer(EventStorage storage) {
        this.storage = storage;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            storage.put();
        }
    }
}

class Consumer implements Runnable {

    private final EventStorage storage;

    public Consumer(EventStorage storage) {
        this.storage = storage;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            storage.take();
        }
    }
}

class EventStorage {

    private final LinkedList<Date> storage;
    private final int maxSize;


    public EventStorage() {
        maxSize = 10;
        this.storage = new LinkedList<>();
    }

    public synchronized void put() {
        while (storage.size() >= maxSize) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        storage.add(new Date());
        System.out.println("仓库里有了 " + storage.size() + " 个产品.");
        notify();
    }

    public synchronized void take() {
        while (storage.size() == 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("拿到了 " + storage.poll() + "，现在仓库还剩下 " + storage.size());
        notify();
    }
}
