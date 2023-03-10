package com.javase.thread.base;

/**
 * @author miclefengzss
 * 2022/12/26 下午2:55
 */
public class Interruption {

    public static volatile boolean mark = true;

    public static void main(String[] args) {

        Thread t1 = new Thread(() -> {
            long i = 0;
            while (mark) {
                i++;
            }
            System.out.println("i = " + i);
        });

        t1.start();

        SleepHelper.sleepSecond(1);

        mark = false;

        Thread t2 = new Thread(() -> {
            long j = 0;
            while (!Thread.interrupted()) {
                j++;
            }
            System.out.println("j = " + j);
        });

        t2.start();
        SleepHelper.sleepSecond(1);
        t2.interrupt();

        System.out.println("===================");

        Thread t3 = new Thread(() -> {
            for (;;) {
                if (Thread.interrupted()) {
                    System.out.println("t3 interrupted");
                    System.out.println(Thread.currentThread().isInterrupted());
                    break;
                }
            }
        });

        t3.start();
        System.out.println("t3 sleep 1 second");
        SleepHelper.sleepSecond(1);
        t3.interrupt();
        SleepHelper.sleepSecond(1);
        System.out.println(t3.isInterrupted());
        System.out.println(t3.isInterrupted());
    }
}
