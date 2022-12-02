package com.javase.thread.base.threadobjectcommonmethods;

/**
 * currentThread 获取当前线程
 */
public class ThreadCurrentThread implements Runnable {

    public static void main(String[] args) {
        ThreadCurrentThread r = new ThreadCurrentThread();
        new ThreadCurrentThread().run();
        new Thread(r).start();
        new Thread(r).start();
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName());
    }
}
