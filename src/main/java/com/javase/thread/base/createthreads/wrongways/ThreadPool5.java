package com.javase.thread.base.createthreads.wrongways;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 线程池创建线程的方式
 * @author miclefengzss
 */
public class ThreadPool5 {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();

        for (int i = 0; i < 1000; i++) {
            executorService.submit(new Tasks() {
            });
        }
    }
}

class Tasks implements Runnable {
    @Override
    public void run() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException ignored) {

        }
        System.out.println(Thread.currentThread().getName());
    }
}