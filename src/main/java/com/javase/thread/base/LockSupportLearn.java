package com.javase.thread.base;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * @author miclefengzss
 * 2023/1/4 下午3:00
 */
public class LockSupportLearn {

    public static void main(String[] args) {

        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                System.out.println(i);
                if (i == 5) {
                    // 让某个线程暂停
                    LockSupport.park();
                }
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        t1.start();
        try {
            TimeUnit.SECONDS.sleep(8);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("After 8 seconds.");

        // 恢复被暂停的线程
        // LockSupport.unpark() 可以先于 LockSupport.park() 执行，执行之后 LockSupport.park() 就不生效。
        LockSupport.unpark(t1);
    }
}
