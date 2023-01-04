package com.javase.thread.oldbase.uncaughtexception;

import java.util.concurrent.TimeUnit;

/**
 *  使用刚才自己写的UncaughtExceptionHandler
 */
public class UseOwnUncaughtExceptionHandler implements Runnable {

    public static void main(String[] args) throws InterruptedException {
        Thread.setDefaultUncaughtExceptionHandler(new MyUncaughtExceptionHandler("自定义捕获器"));
        UseOwnUncaughtExceptionHandler r = new UseOwnUncaughtExceptionHandler();
        new Thread(r, "线程1").start();
        TimeUnit.MILLISECONDS.sleep(100);
        new Thread(r, "线程2").start();
        TimeUnit.MILLISECONDS.sleep(100);
        new Thread(r, "线程3").start();
        TimeUnit.MILLISECONDS.sleep(100);
        new Thread(r, "线程4").start();
    }

    @Override
    public void run() {
        throw new RuntimeException();
    }
}
