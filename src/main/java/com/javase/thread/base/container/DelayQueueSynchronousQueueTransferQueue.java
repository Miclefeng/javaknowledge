package com.javase.thread.base.container;


import java.util.concurrent.*;
import java.util.concurrent.locks.LockSupport;

/**
 * @author miclefengzss
 * 2023/1/11 上午11:23
 */
public class DelayQueueSynchronousQueueTransferQueue {

    static DelayQueue<MyTask> tasks = new DelayQueue<>();

    static SynchronousQueue<String> synchronousQueue = new SynchronousQueue<>();

    static LinkedTransferQueue<String> transferQueue = new LinkedTransferQueue<>();

    static class MyTask implements Delayed {

        String name;

        long runningTime;

        public MyTask(String name, long runningTime) {
            this.name = name;
            this.runningTime = runningTime;
        }

        @Override
        public long getDelay(TimeUnit unit) {
            return unit.convert(runningTime - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
        }

        @Override
        public int compareTo(Delayed o) {
            if (this.getDelay(TimeUnit.MILLISECONDS) < o.getDelay(TimeUnit.MILLISECONDS)) {
                return -1;
            } else if (this.getDelay(TimeUnit.MILLISECONDS) > o.getDelay(TimeUnit.MILLISECONDS)) {
                return 1;
            }
            return 0;
        }

        @Override
        public String toString() {
            return "MyTask{" +
                    "name='" + name + '\'' +
                    ", runningTime=" + runningTime +
                    '}';
        }
    }

    public static void main(String[] args) {
        long currentTime = System.currentTimeMillis();

        MyTask t1 = new MyTask("t1", currentTime + 1000);
        MyTask t2 = new MyTask("t2", currentTime + 1500);
        MyTask t3 = new MyTask("t3", currentTime + 2000);
        MyTask t4 = new MyTask("t4", currentTime + 500);
        MyTask t5 = new MyTask("t5", currentTime + 2500);

        tasks.put(t1);
        tasks.put(t2);
        tasks.put(t3);
        tasks.put(t4);
        tasks.put(t5);

        System.out.println(tasks);

        for (int i = 0; i < 5; i++) {
            try {
                System.out.println(tasks.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("==============================================");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(() -> {
            try {
                String take = synchronousQueue.take();
                System.out.println(Thread.currentThread().getName() + " take: " + take);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "t1").start();

        new Thread(() -> {
            try {
                synchronousQueue.put("synchronous"); // 阻塞等待，容量为 0，线程之间手把手交换数据
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " queue size: " + synchronousQueue.size());
        }, "t2").start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("=======================");

        new Thread(() -> {
            try {
                // take 阻塞的，底层是 LockSupport.park LockSupport.unpark
                System.out.println(Thread.currentThread().getName() + " transfer take1: " + transferQueue.take());
                System.out.println(Thread.currentThread().getName() + " transfer take2: " +transferQueue.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "t3").start();

        new Thread(() -> {
            try {
                // transfer 阻塞的，底层是 LockSupport.park LockSupport.unpark
                transferQueue.transfer("transfer");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "t4").start();

        new Thread(() -> {
            try {
                transferQueue.transfer("transfer");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "t5").start();
    }
}
