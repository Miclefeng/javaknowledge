package com.javase.thread.base;

import java.util.concurrent.*;

/**
 * @author miclefengzss
 * 2022/12/26 上午11:09
 */
public class StartThreadWay {

    static class MyThread extends Thread {
        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + ": MyThread Extends Thread Run.");
        }
    }

    static class MyRunnable implements Runnable {
        @Override
        public void run() {
            System.out.println("---------------------------------------------------");
            System.out.println(Thread.currentThread().getName() + ": MyRunnable implements Runnable Run.");
        }
    }

    static class MyCallable implements Callable<String> {
        @Override
        public String call() {
            System.out.println("---------------------------------------------------");
            System.out.println(Thread.currentThread().getName() + ": MyCallable implements Callable Run.");
            return "callable running.";
        }
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // 1、继承 Thread 类 重写 run 方法
        new MyThread().start();
        // 2、实现 runnable 接口
        new Thread(new MyRunnable()).start();
        // 3、lambda 表达式
        new Thread(() -> {
            System.out.println("---------------------------------------------------");
            System.out.println(Thread.currentThread().getName() + ": Lambda Run.");
        }).start();
        // 4、ThreadPoolExecutor
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(1, 2, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingDeque<>(), new ThreadPoolExecutor.DiscardPolicy());
        threadPoolExecutor.execute(() -> {
            System.out.println("---------------------------------------------------");
            System.out.println(Thread.currentThread().getName() + ": ThreadPoolExecutor Run.");
        });
        // 5、Future Callable and FutureTask
        Future<String> f = threadPoolExecutor.submit(new MyCallable());
        System.out.println(f.get());

        FutureTask<String> futureTask = new FutureTask<>(new MyCallable());
        new Thread(futureTask).start();
        System.out.println(futureTask.get());

        threadPoolExecutor.shutdown();
    }
}
