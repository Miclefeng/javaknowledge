package com.javase.thread.base.pool;

import java.util.Date;
import java.util.concurrent.*;

/**
 * @author miclefengzss
 * 2023/2/3 上午10:11
 */
public class CallableFutureExecutor {

    public static void main(String[] args) throws InterruptedException, ExecutionException, TimeoutException {

        ExecutorService executorService = Executors.newFixedThreadPool(2);

        Future<?> f = executorService.submit(() -> {
            System.out.println("T1 entry.");
            try {
                System.out.println(new Date());
                TimeUnit.MILLISECONDS.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(new Date());
            System.out.println("T1 exist.");
        });

        try {
            f.get(2, TimeUnit.SECONDS);
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            e.printStackTrace();
        }

        System.out.println(f.isDone());
        if (!f.isDone()) {
            f.cancel(true);
        }


        Future<?> f2 = executorService.submit(() -> {
            System.out.println("T2 entry.");
            try {
                System.out.println(new Date());
                TimeUnit.MILLISECONDS.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(new Date());
            System.out.println("T2 exist.");
        });

        Future<?> f3 = executorService.submit(() -> {
            System.out.println("T3 entry.");
            try {
                System.out.println(new Date());
                TimeUnit.MILLISECONDS.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(new Date());
            System.out.println("T3 exist.");
        });

        f2.get();
        f3.get();

        executorService.shutdown();
    }
}
