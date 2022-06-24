package com.thread.base.createthreads;

/**
 * 使用 Thread 方式实现线程
 * @author miclefengzss
 */
public class ThreadStyle extends Thread {

    public static void main(String[] args) {
        new ThreadStyle().start();
    }

    @Override
    public void run() {
        System.out.println("用 Thread 类方式实现线程");
    }
}
