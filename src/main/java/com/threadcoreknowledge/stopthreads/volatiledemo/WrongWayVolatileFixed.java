package com.threadcoreknowledge.stopthreads.volatiledemo;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * 用中断来修复无尽等待问题
 */
public class WrongWayVolatileFixed {

    public static void main(String[] args) throws InterruptedException {
        WrongWayVolatileFixed wrongWayVolatileFixed = new WrongWayVolatileFixed();

        ArrayBlockingQueue storage = new ArrayBlockingQueue(10);

        Producer producer = wrongWayVolatileFixed.new Producer(storage);
        Thread producerThread = new Thread(producer);
        producerThread.start();
        Thread.sleep(1000);

        Consumer consumer = wrongWayVolatileFixed.new Consumer(storage);
        while (consumer.needConsumer()) {
            System.out.println(consumer.storage.take() + " 被消费了.");
            Thread.sleep(100);
        }
        System.out.println("消费者不需要更多的数据了.");

        producerThread.interrupt();
    }

    class Producer implements Runnable {

        BlockingQueue storage;

        public Producer(BlockingQueue storage) {
            this.storage = storage;
        }

        @Override
        public void run() {
            int num = 0;
            try {
                while (num <= 10000 && !Thread.currentThread().isInterrupted()) {
                    if (num % 100 == 0) {
                        this.storage.put(num);
                        System.out.println(num + " 是100的倍数，被放入仓库了.");
                    }
                    num++;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println("生产者运行结束");
            }
        }
    }

    class Consumer {

        BlockingQueue storage;

        public Consumer(BlockingQueue storage) {
            this.storage = storage;
        }

        public boolean needConsumer() {
            if (Math.random() > 0.95) {
                return false;
            }
            return true;
        }
    }
}