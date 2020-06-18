package com.synchronizedlearn;

public class SynchronizedDifferentMethod implements Runnable {

    public static SynchronizedDifferentMethod instance = new SynchronizedDifferentMethod();

    public static void main(String[] args) {
        /**
         * 同时访问同一个对象的不同的同步方法，会发生锁的等待
         * 因为synchronized 修饰普通方法默认给对象 this 加锁，所以发生锁等待
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

    public synchronized void method1() {
        System.out.println("我是Method1：" + Thread.currentThread().getName());
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "运行结束.");
    }

    public synchronized void method2() {
        System.out.println("我是Method2：" + Thread.currentThread().getName());
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "运行结束.");
    }
}
