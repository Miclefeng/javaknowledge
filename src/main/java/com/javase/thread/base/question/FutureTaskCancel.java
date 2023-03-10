package com.javase.thread.base.question;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author miclefengzss
 * 2023/2/22 下午3:56
 */
public class FutureTaskCancel {

    private static List<Future> futureTaskList = new ArrayList<>(10);

    public static ReentrantLock takeLock = new ReentrantLock();

    public static void take() throws InterruptedException {
        takeLock.lockInterruptibly();
        try {
            Thread.sleep(5000);
        } finally {
            takeLock.unlock();
        }
    }

    static class MyRunnable implements Runnable {

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + " entry: " + new Date());
            try {
                take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " exit: " + new Date());
        }
    }

    public static void main(String[] args) {

        Thread t = new Thread(new MyRunnable());

        t.start();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t.interrupt();

        System.out.println("========================");


        ExecutorService executorService = Executors.newFixedThreadPool(2);

//        for (int i = 0; i < 10; i++) {
//            futureTaskList.add(executorService.submit(() -> {
//                System.out.println(Thread.currentThread().getName() + " entry." + new Date());
//                try {
//                    Thread.sleep(120000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                System.out.println(Thread.currentThread().getName() + " exit." + new Date());
//            }));
//        }

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        int i = 0;
        for (Future f : futureTaskList) {
//            try {
            f.cancel(true);
//                f.get();
//            } catch (InterruptedException | ExecutionException e) {
//                e.printStackTrace();
//            }
            i++;
        }
    }
}
