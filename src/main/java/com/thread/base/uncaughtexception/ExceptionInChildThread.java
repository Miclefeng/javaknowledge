package com.thread.base.uncaughtexception;

import java.util.concurrent.TimeUnit;

/**
 * 单线程，抛出，处理，有异常堆栈
 * 多线程，子线程发生异常 父线程不受影响
 */
public class ExceptionInChildThread implements Runnable {

    public static void main(String[] args) {

        new Thread(new ExceptionInChildThread()).start();

        for (int i = 0; i < 300; i++) {
            System.out.println(i);
        }
    }

    @Override
    public void run() {
        try {
            TimeUnit.MILLISECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        throw new RuntimeException();
    }
}
