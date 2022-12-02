package com.javase.thread;

import org.openjdk.jol.info.ClassLayout;

/**
 * @author miclefengzss
 * 2022/8/19 上午10:35
 */
public class SynchronizedHeader {

    public static void main(String[] args) throws InterruptedException {
//        Thread.sleep(5000);
        Object o = new Object();
        // 匿名偏向锁，偏向锁默认有个5秒延迟开启，开启后就不会出现无锁状态
        System.out.println(ClassLayout.parseInstance(o).toPrintable());

        new Thread(() -> {
            synchronized (o) {
                System.out.println("T1: " + ClassLayout.parseInstance(o).toPrintable());
            }
        }, "T1").start();

        synchronized (o) {
            System.out.println("Main: " + ClassLayout.parseInstance(o).toPrintable());
        }
    }
}
