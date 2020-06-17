package com.synchronizedlearn;

/**
 * 对象锁
 * synchronized 修饰代码块
 */
public class SynchronizedObjectCodeBlock implements Runnable {

    public static SynchronizedObjectCodeBlock instance = new SynchronizedObjectCodeBlock();
    public Object lock1 = new Object();
    public Object lock2 = new Object();

    public static void main(String[] args) {
        Thread t1 = new Thread(instance);
        Thread t2 = new Thread(instance);
        t1.start();
        t2.start();

//        t1.join();
//        t2.join();
        while (t1.isAlive() || t2.isAlive()) {

        }

        System.out.println("Finished!");
    }

    @Override
    public void run() {
        synchronized (lock1) {
            System.out.println("我是Lock1(对象锁的代码块形式): " + Thread.currentThread().getName());
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " ,Lock1 运行结束.");
        }

        synchronized (lock2) {
            System.out.println("我是Lock2(对象锁的代码块形式): " + Thread.currentThread().getName());
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " ,Lock2 运行结束.");
        }
    }
}
