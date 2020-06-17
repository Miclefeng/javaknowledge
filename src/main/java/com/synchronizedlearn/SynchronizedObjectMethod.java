package com.synchronizedlearn;

/**
 * 对象锁
 * synchronized 修饰普通方法，锁对象默认为 this
 */
public class SynchronizedObjectMethod implements Runnable {

    public static SynchronizedObjectMethod instance = new SynchronizedObjectMethod();

    public static void main(String[] args) {
        Thread t1 = new Thread(instance);
        Thread t2 = new Thread(instance);
        t1.start();
        t2.start();

        while (t1.isAlive() || t2.isAlive()) {

        }
        System.out.println("Finished");
    }

    @Override
    public void run() {
        method();
    }

    public synchronized void method() {
        System.out.println("我是对象锁的方法修饰符形式：" + Thread.currentThread().getName());
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "运行结束.");
    }
}
