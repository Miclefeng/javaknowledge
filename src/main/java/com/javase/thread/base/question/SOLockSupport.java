package com.javase.thread.base.question;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.LockSupport;

/**
 * @Description: some desc
 * @Author: miclefengzss
 * @Date: 2023/11/17 16:23
 */
public class SOLockSupport {

    private static char cs = 'A';
    private static int is = 1;

    static Thread t1, t2;

    public static void main(String[] args) {

        t1 = new Thread(() -> {
            for (int i = 0; i < 26; i++) {
                System.out.print(cs);
                cs++;
                LockSupport.park();
                LockSupport.unpark(t2);
            }
        }, "T1");

        t2 = new Thread(() -> {
            for (int i = 0; i < 26; i++) {
                LockSupport.park();
                System.out.print(is + " ");
                is++;
                LockSupport.unpark(t2);
            }
        }, "T1");

        t2.start();
        t1.start();
    }
}
