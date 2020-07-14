package com.threadcoreknowledge.stopthreads.volatiledemo;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * 陷入阻塞时，volatile 是无法停止线程的，
 * 此例中，生产者的生产速度很快，消费者消费速度慢，所以阻塞队列满了之后，
 * 生产者会产生阻塞，等待消费者进一步消费
 * <p>
 * 如果我们遇到了线程长时间阻塞，就没有办法及时唤醒它，或者永远无法唤醒它,
 * 而interrupt就是把 wait 等长时间阻塞作为一种特殊的情况处理了，所以我们
 * 应该用 interrupt 思维来停止线程.
 */
public class WrongWayVolatileCantStop {

    public static void main(String[] args) throws InterruptedException {
        ArrayBlockingQueue<Integer> storage = new ArrayBlockingQueue<>(10);

        Producer producer = new Producer(storage);
        Thread producerThread = new Thread(producer);
        producerThread.start();
        Thread.sleep(1000);

        Consumer consumer = new Consumer(storage);
        while (consumer.needMoreNums()) {
            System.out.println(consumer.storage.take() + "被消费了.");
            Thread.sleep(100);
        }
        System.out.println("消费者不需要更多的数据了.");

        producer.canceled = true;
        System.out.println(producer.canceled);
    }
}

class Producer implements Runnable {

    public volatile boolean canceled = false;

    BlockingQueue<Integer> storage;

    public Producer(BlockingQueue<Integer> storage) {
        this.storage = storage;
    }

    @Override
    public void run() {
        int num = 0;
        try {
            while (num <= 100000 && !canceled) {
                if (num % 100 == 0) {
                    // put 产生阻塞一直等待消费
                    storage.put(num);
                    System.out.println(num + " 是100的倍数,被放到仓库中了.");
                }
                num++;
                // 每 100 次放入一个数据正好 sleep 100ms, 外部循环 sleep 100ms 无法达到阻塞状态
//                Thread.sleep(1);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println("生产者停止运行.");
        }
    }
}

class Consumer {

    BlockingQueue<Integer> storage;

    public Consumer(BlockingQueue<Integer> storage) {
        this.storage = storage;
    }

    public boolean needMoreNums() {
        if (Math.random() > 0.95) {
            return false;
        }
        return true;
    }
}
