package com.javase.thread.base.pool;

import io.netty.util.concurrent.DefaultThreadFactory;

import java.util.concurrent.*;

/**
 * @author miclefengzss
 * 2023/2/15 下午2:34
 */
public class MyThreadPoolExecutor {

    public static void main(String[] args) {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(1, 1, 0, TimeUnit.MILLISECONDS, new LinkedBlockingDeque<>(), r -> {
            Thread t = new Thread(r);
            t.setDaemon(true);
            return t;
        });

        executor.execute(() -> {
            System.out.println("Entry:");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Exist.");
        });

        executor.shutdown();

        try {
            executor.awaitTermination(1, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
