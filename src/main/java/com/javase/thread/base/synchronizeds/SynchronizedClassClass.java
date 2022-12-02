package com.javase.thread.base.synchronizeds;

/**
 * synchronized(*.class) 代码块 类锁
 *
 * 锁在对象头monitor中，所以锁对象不能为空
 * 1、一个锁同时只能被一个线程获取，没拿到锁的线程需要等待
 * 2、每个实例都有自己的一把锁，不同的实例之间互不影响；例外：
 *  锁对象是 synchronized(*.class) 或者 synchronized static方法，所有的对象共用一把锁
 * 3、无论方法正常执行完毕还是抛出异常，锁都会释放
 */
public class SynchronizedClassClass implements Runnable {

    public static SynchronizedClassClass instance1 = new SynchronizedClassClass();
    public static SynchronizedClassClass instance2 = new SynchronizedClassClass();

    public static void main(String[] args) {
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

    public void method() {
        synchronized (SynchronizedClassClass.class) {
            System.out.println("我是类锁 synchronized(*.class) 形式：" + Thread.currentThread().getName());
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "运行结束.");
        }
    }
}
