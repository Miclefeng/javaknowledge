package com.javase.thread.base;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * @author miclefengzss
 * 2023/1/4 下午3:00
 */
public class LockSupportSemaphore {

    public static void main(String[] args) {

        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                System.out.println(i);
                if (i == 5) {
                    // 让某个线程暂停
                    LockSupport.park();
                }
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        t1.start();
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("After 2 seconds.");

        // 恢复被暂停的线程
        // LockSupport.unpark() 可以先于 LockSupport.park() 执行，执行之后 LockSupport.park() 就不生效。
        LockSupport.unpark(t1);
        millisSleep(2000);
        System.out.println("=========================");
        usingSemaphore();
    }

    public static void usingSemaphore() {
        Semaphore s = new Semaphore(1);
        Thread t1 = new Thread(()->{
            System.out.println("t1 start.");
            try {
                s.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            millisSleep(3000);
            System.out.println("t1 sleep 3 second end.");
            s.release();
            System.out.println("t1 end.");
        });

        Thread t2 = new Thread(() -> {
            System.out.println("t2 start.");
            try {
                s.acquire();
                System.out.println("t2 acquire.");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            millisSleep(1000);
            s.release();
            System.out.println("t2 end.");
        });

        t1.start();
        t2.start();
    }

    public static void millisSleep(long time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
