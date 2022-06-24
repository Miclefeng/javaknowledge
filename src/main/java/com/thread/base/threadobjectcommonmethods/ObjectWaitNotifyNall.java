package com.thread.base.threadobjectcommonmethods;

/**
 * 3个线程， 线程1和2 首先被阻塞，线程3唤醒它们，start先执行不代表线程先启动。
 */
public class ObjectWaitNotifyNall implements Runnable {

    private static final Object resource = new Object();

    public static void main(String[] args) {
        Runnable r = new ObjectWaitNotifyNall();
        Thread t1 = new Thread(r);
        Thread t2 = new Thread(r);

        Thread t3 = new Thread(() -> {
            synchronized (resource) {
                resource.notifyAll();
//                resource.notify();
                System.out.println(Thread.currentThread().getName() + " Thread3 notified.");
            }
        });

        t1.start();
        t2.start();
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(t1.getState());
        System.out.println(t2.getState());
        t3.start();
    }

    @Override
    public void run() {
        synchronized (resource) {
            System.out.println(Thread.currentThread().getName() + " get resource lock.");
            try {
                System.out.println(Thread.currentThread().getName() + " waiting to release.");
                resource.wait();
                System.out.println(Thread.currentThread().getName() + " waiting to end.");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
