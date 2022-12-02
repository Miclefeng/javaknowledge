package com.javase.thread.base.synchronizeds;

public class SynchronizedStaticAndNormal implements Runnable {

    public static SynchronizedStaticAndNormal instance = new SynchronizedStaticAndNormal();

    public static void main(String[] args) {
        /**
         * synchronized 修饰静态方法 修饰的是 class 对象
         * synchronized 修饰普通方法 修饰的是 this 对象
         * 因为加锁的对象不同，所以不会发生锁的等待
         */
        Thread t1 = new Thread(instance);
        Thread t2 = new Thread(instance);
        t1.start();
        t2.start();

        while (t1.isAlive() || t2.isAlive()) {

        }
        System.out.println("Finished.");
    }

    @Override
    public void run() {
        if (Thread.currentThread().getName().equals("Thread-0")) {
            method1();
        } else {
            method2();
        }
    }

    public static synchronized void method1() {
        System.out.println("我是static Method：" + Thread.currentThread().getName());
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "运行结束.");
    }

    public synchronized void method2() {
        System.out.println("我是normal Method：" + Thread.currentThread().getName());
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "运行结束.");
    }
}
