package com.javase.thread.base.pool;


import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author miclefengzss
 * 2023/2/20 上午9:51
 */
public class MyScheduledThreadPoolExecutor {

    public static void main(String[] args) {
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);

        scheduledExecutorService.schedule(() -> {
            System.out.println(new Date());
        }, 3 , TimeUnit.SECONDS);

//        scheduledExecutorService.scheduleAtFixedRate(() -> {
//            System.out.println(new Date());
//        }, 0, 2, TimeUnit.SECONDS);

//        scheduledExecutorService.scheduleWithFixedDelay(() -> {
//            System.out.println(new Date());
//            try {
//                Thread.sleep(3000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }, 0, 2, TimeUnit.SECONDS);

        System.out.println(-TimeUnit.SECONDS.toNanos(-2) - (Long.MAX_VALUE >> 1));

    }
}
