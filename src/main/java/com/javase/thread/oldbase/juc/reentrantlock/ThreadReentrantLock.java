package com.javase.thread.oldbase.juc.reentrantlock;


import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author miclefengzss
 * 2021/12/6 上午11:31
 */
public class ThreadReentrantLock {

    /**
     * 必须手动释放锁，synchronized锁定如果遇到异常，jvm会自动释放锁，但是lock必须手动释放
     * 1、可重入锁
     * 2、可以进行tryLock(5,TimeUnit.Second)
     * 3、使用ReentrantLock 可以调用 lockInterruptibly 方法，对线程interrupt，在一个线程等待锁的过程汇总，可以被打断
     * 4、设置为true为公平锁，新来的线程会先检查等待队列是否有线程，如果有进入等待队列
     * 等待，没有则竞争去抢锁
     */
    private static ReentrantLock lock = new ReentrantLock(true);


    public void m1() {
        lock.lock();
        try {
            for (int i = 0; i < 3; i++) {
                System.out.println("m1... : " + i);
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void m2() {
        boolean locked = false;
        try {
            locked = lock.tryLock(4, TimeUnit.SECONDS);
            System.out.println("m2 ..." + locked);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if (locked) {
                lock.unlock();
            }
        }
    }

    public void m3() {
        lock.lock();
        try {
            System.out.println("m3... start");
            TimeUnit.SECONDS.sleep(Integer.MAX_VALUE);
            System.out.println("m3... end.");
        } catch (InterruptedException e) {
            System.out.println("m3... 被中断");
        } finally {
            lock.unlock();
        }
    }

    public void m4() {
        try {
            lock.lockInterruptibly();
            System.out.println("m4... start");
        } catch (InterruptedException e) {
            System.out.println("m4... 被中断");
        } finally {
            try {
                lock.unlock();
            } catch (Exception e) {
                System.out.println("m4... 没有得到锁线程运行结束。");
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ThreadReentrantLock trl = new ThreadReentrantLock();

//        new Thread(trl::m1).start();
//        new Thread(trl::m2).start();

        final Thread t3 = new Thread(trl::m3);
        t3.start();
        Thread.sleep(3000);
        final Thread t4 = new Thread(trl::m4);
        t4.start();
        t3.interrupt();


//        new Thread(() -> {
//            for (int i = 0; i < 100; i++) {
//                lock.lock();
//                try {
//                    System.out.println(Thread.currentThread().getName() + " : " + i);
//                } finally {
//                    lock.unlock();
//                }
//            }
//        }).start();
//        new Thread(() -> {
//            for (int i = 0; i < 100; i++) {
//                lock.lock();
//                try {
//                    System.out.println(Thread.currentThread().getName() + " : " + i);
//                } finally {
//                    lock.unlock();
//                }
//            }
//        }).start();
    }
}
