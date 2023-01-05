package com.javase.thread.base.question;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.LockSupport;

/**
 * @author miclefengzss
 * 2023/1/5 下午3:54
 */
public class SequentialOutputLockSupport {

    static char cs = 'A';
    static int is = 1;
    static int ics = cs;

    static Thread t1 = null, t2 = null;

    public static void main(String[] args) {

        t1 = new Thread(() -> {
            for (int i = 0; i < 26; i++) {
                System.out.print(cs + " ");
                ics++;
                cs = (char) ics;
                LockSupport.unpark(t2);
                LockSupport.park();
            }
        });

        t2 = new Thread(() -> {
            for (int i = 0; i < 26; i++) {
                LockSupport.park();
                System.out.print(is + " | ");
                is++;
                LockSupport.unpark(t1);
            }
        });

        t2.start();
        t1.start();
    }
}
