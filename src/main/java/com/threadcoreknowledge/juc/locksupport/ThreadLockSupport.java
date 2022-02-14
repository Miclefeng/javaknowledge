package com.threadcoreknowledge.juc.locksupport;

import java.util.concurrent.locks.LockSupport;

/**
 * @author miclefengzss
 * 2021/12/7 下午2:07
 */
public class ThreadLockSupport {

    /**
     * LockSupport 用来创建锁和其他同步工具类的基本线程阻塞原语
     * Java锁和同步器框架的核心AQS(AbstractQueuedSynchronizer 抽象队列同步器)
     * 就是通过调用LockSupport.park()和LockSupport.unpark()实现线程的阻塞和唤醒的。
     * <p>
     * park()方法会阻塞当前线程(线程进入Waiting状态)，除非它获取了"许可证"。
     * unpark(Thread t)方法会给线程t颁发一个"许可证"。
     * <p>
     * LockSupport比Object的wait/notify有两大优势:
     * ①LockSupport不需要在同步代码块里 。所以线程间也不需要维护一个共享的同步对象了，实现了线程间的解耦。
     * ②unpark函数可以先于park调用，所以不需要担心线程间的执行的先后顺序。
     * <p>
     * park和wait的区别。wait让线程阻塞前，必须通过synchronized获取同步锁。
     *
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {

        Thread t = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                if (i == 5) {
                    LockSupport.park();
                }
                System.out.println(i);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        t.start();

        Thread.sleep(8000);
        System.out.println("After 8's later.");
        LockSupport.unpark(t);
    }
}
