package com.javase.thread.base.synchronizeds;

/**
 * 类锁
 * 1、只有一个类对象，所谓的类锁就是 class 对象的锁
 * 类锁只能在同一时刻被一个对象所拥有
 */
public class SynchronizedClassStatic implements Runnable {

    public static SynchronizedClassStatic instance1 = new SynchronizedClassStatic();
    public static SynchronizedClassStatic instance2 = new SynchronizedClassStatic();

    public static void main(String[] args) {
        /**
         * 两个线程访问不同对象的synchronized静态方法会发生锁等待
         * 因为synchronized 修饰的static方法，产生的是类锁，所以会等待锁
         */
        Thread t1 = new Thread(instance1);
        Thread t2 = new Thread(instance2);
        t1.start();
        t2.start();

        while (t1.isAlive() || t2.isAlive()) {

        }
        System.out.println("Finished.");
    }

    @Override
    public void run() {
        method();
    }

    public static synchronized void method() {
        System.out.println("我是类锁 static 修饰方法的 形式：" + Thread.currentThread().getName());
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "运行结束.");
    }
}
