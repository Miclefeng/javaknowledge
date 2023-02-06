package com.javase.thread.base.container;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * @author miclefengzss
 * 2023/1/11 上午10:24
 */
public class ArrayBlockingQueueLinkedBlockingQueue {

    static ArrayBlockingQueue<String> arrayBlockingQueue = new ArrayBlockingQueue<>(10);

    // 阻塞队列
    static LinkedBlockingDeque<String> linkedBlockingDeque = new LinkedBlockingDeque<>();

    public static void main(String[] args) {
        for (int i = 0; i < 9; i++) {
            arrayBlockingQueue.add(i + " ");
        }

        arrayBlockingQueue.add("10 ");  // add 如果超出容器容量会报异常
        arrayBlockingQueue.offer("11 "); // offer 成功添加返回 true， 失败返回 false
        arrayBlockingQueue.poll();
        arrayBlockingQueue.forEach(System.out::print);
        System.out.println();
        System.out.println("=================================");

        for (int i = 0; i < 3; i++) {
            new Thread(() -> {
                for (int j = 0; j < Integer.MAX_VALUE; j++) {
                    try {
                        // put 如果队列满了 阻塞等待消费
                        linkedBlockingDeque.put(j + " ");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }

        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                for (int j = 0; j < 10000; j++) {
                    try {
                        // take 方法是阻塞的，如果队列中没有元素，阻塞等待(lock.await)
                        System.out.println(linkedBlockingDeque.take() + ", size: " + linkedBlockingDeque.size());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }
}
