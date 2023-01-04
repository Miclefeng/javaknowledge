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

        SleepHelper.sleepSecond(3);

        mark = false;


        Thread t2 = new Thread(() -> {
            long j = 0;
            while (!Thread.interrupted()) {
                j++;
            }
            System.out.println("j = " + j);
        });

        t2.start();
        SleepHelper.sleepSecond(3);
        t2.interrupt();
    }
}
