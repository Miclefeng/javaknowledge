package com.thread.base.threadobjectcommonmethods;

/**
 * wait 和 notify 用法
 * 1、 代码执行顺序
 *  t1 获取到 synchronized锁开始执行，然后执行 wait() 释放了 synchronized 锁，t2 获取到 synchronized 锁，开始执行 notify()
 *  t2 会在执行完毕后释放 synchronized 锁，t1 便会拿到 synchronized 锁，开始后续执行
 * 2、 wait 释放 synchronized 加的 monitor 锁
 *
 *    wait() 需要放在同步代码块中使用，保证 notify()/wait() 的交替运行，
 * 避免notify()先执行导致死锁问题
 *    wait()/notify()/notifyAll() 定义在 Object 里面，是锁级别的操作，锁是绑定在某个
 * 对象上，这样一个线程就可以获取多个锁。
 *    Thread.wait Thread类特殊，会在线程结束的时候调用notify()
 *
 */
public class ObjectWait {

    public static Object object = new Object();

    public static void main(String[] args) {
        Thread1 t1 = new Thread1();
        Thread2 t2 = new Thread2();
        t1.start();
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t2.start();
    }

    static class Thread1 extends Thread {
        @Override
        public void run() {
            synchronized (object) {
                System.out.println(Thread.currentThread().getName() + "开始运行.");
                try {
                    object.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "获取到了 synchronized 锁.");
            }
        }
    }

    static class Thread2 extends Thread {
        @Override
        public void run() {
            synchronized (object) {
                object.notify();
                System.out.println("线程" + Thread.currentThread().getName() + "执行了 notify().");
            }
        }
    }
}
