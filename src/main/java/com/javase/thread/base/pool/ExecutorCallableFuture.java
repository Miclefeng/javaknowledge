package com.javase.thread.base.pool;

import java.util.concurrent.*;

/**
 * @author miclefengzss
 * 2023/2/3 上午10:11
 */
public class ExecutorCallableFuture {

    public static void main(String[] args) {
        Callable<String> callable = () -> "Hello Callable.";
        final ExecutorService service = Executors.newCachedThreadPool();
        Future<String> future = service.submit(callable);
        try {
            System.out.println(future.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        service.shutdown();

        System.out.println("========================");

        FutureTask<Integer> task = new FutureTask<>(() -> {
            Thread.sleep(1000);
            return 1000;
        });

        new Thread(task).start();
        try {
            System.out.println(task.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        System.out.println("========================");

        final ExecutorService executorService = Executors.newFixedThreadPool(5);
        Future<Integer> f = executorService.submit(() -> {
            TimeUnit.MILLISECONDS.sleep(500);
            return 1;
        });

        try {
            System.out.println(f.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println(f.isDone());
        executorService.shutdown();
    }
}
