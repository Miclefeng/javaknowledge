package com.javase.thread.base.createthreads;

/**
 * 同时使用 Runnable 和 Thread 方式实现线程
 * @author miclefengzss
 */
public class BothRunnableThread {

    public static void main(String[] args) {
        new Thread(() -> System.out.println("我来自 Runnable")) {
            @Override
            public void run() {
               System.out.println("我来自 Thread");
            }
        }.start();
    }
}
