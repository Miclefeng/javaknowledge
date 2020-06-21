package com.threadcoreknowledge.threadobjectcommonmethods;

/**
 * 通过 wait/notify 实现轮流打印奇偶数
 */
public class PrintOddEvenByWaitNotify {

    private static int count = 0;
    private static final Object lock = new Object();

    public static void main(String[] args) {
        new Thread(new TurningRunner(), "偶数").start();
        new Thread(new TurningRunner(), "奇数").start();
    }

    static class TurningRunner implements Runnable {

        @Override
        public void run() {
            while (count <= 100) {
                synchronized (lock) {
                    System.out.println(Thread.currentThread().getName() + ": " + count++);
                    lock.notify();
                    // 判断边界，否则无法停止线程，一直陷入等待
                    if (count <= 100) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                }
            }
        }
    }
}
