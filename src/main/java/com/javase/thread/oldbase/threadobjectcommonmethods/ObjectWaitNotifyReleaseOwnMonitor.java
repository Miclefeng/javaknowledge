package com.javase.thread.oldbase.threadobjectcommonmethods;

/**
 * 证明 wait 只释放当前的那把锁
 * wait, notify, notifyAll
 * 1、必须先拥有monitor 也就是有 synchronized 修饰
 * 2、notify 只能唤醒一个，取决于jvm
 * 3、都属于 object，任何对象都可以调用，无法被重写
 * 4、用 Condition 比较合适
 * 5、只能释放当前的那把锁，避免死锁
 */
public class ObjectWaitNotifyReleaseOwnMonitor {

    private static volatile Object resourceA = new Object();
    private static volatile Object resourceB = new Object();

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            synchronized (resourceA) {
                System.out.println("T1 got resourceA lock.");
                synchronized (resourceB) {
                    System.out.println("T1 got resourceB lock.");
                    try {
                        System.out.println("T1 release resourceA lock.");
                        resourceA.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        Thread t2 = new Thread(() -> {
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (resourceA) {
                System.out.println("T2 got resourceA lock.");
                System.out.println("T2 tries to resourceB lock.");
                synchronized (resourceB) {
                    System.out.println("T2 got resourceB lock.");
                }
            }
        });

        t1.start();
        t2.start();
    }
}
