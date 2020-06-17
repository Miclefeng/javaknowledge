package com.synchronizedlearn;

/**
 * synchronized(*.class) 代码块 类锁
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
