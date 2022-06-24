package com.thread.base.synchronizeds;

public class SynchronizedMethodYesAndNo implements Runnable {

    public static SynchronizedMethodYesAndNo instance = new SynchronizedMethodYesAndNo();

    public static void main(String[] args) {

        /**
         * 同时访问同一个对象的同步方法 和 非同步方法 不会发生锁的等待
         * synchronized 只作用于被修饰的方法，非同步方法不受影响
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
        System.out.println("我是synchronized修饰的方法：" + Thread.currentThread().getName());
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "运行结束.");
    }

    public void method2() {
        System.out.println("我是普通方法：" + Thread.currentThread().getName());
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "运行结束.");
    }
}
