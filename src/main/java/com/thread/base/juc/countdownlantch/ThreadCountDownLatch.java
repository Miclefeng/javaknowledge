package com.thread.base.juc.countdownlantch;

import java.util.concurrent.*;

/**
 * @author miclefengzss
 * 2021/12/6 下午2:56
 */
public class ThreadCountDownLatch {

    /**
     * CountDownLatch 类在创建实例的时候，需要传递倒数次数。倒数到0的时候，之前等待的线程会继续运行
     * CountDownLatch不能回滚重置
     * CountDownLatch用于事件
     * <p>
     * 两种经典用法： 一等多和多等一
     *
     * @param args
     */
    public static void main(String[] args) throws InterruptedException {
        // 所有比赛的人等待裁判发令
        CountDownLatch beginLatch = new CountDownLatch(1);
        // 等待所有人都到终点比赛结束
        CountDownLatch endLatch = new CountDownLatch(5);

        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(5,
                5,
                60L,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(1000),
                new ThreadPoolExecutor.DiscardPolicy());

        for (int i = 0; i < 5; i++) {
            final int no = i + 1;
            Runnable runnable = () -> {
                try {
                    beginLatch.await();
                    System.out.println("No." + no + " 开始跑步了。");
                    Thread.sleep((long) (Math.random() * 10000));
                    System.out.println("No." + no + "跑到了终点。");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    endLatch.countDown();
                }
            };
            threadPoolExecutor.submit(runnable);
        }
        Thread.sleep(3000);
        System.out.println("裁判发令枪响，开始比赛。");
        beginLatch.countDown();

        endLatch.await();
        System.out.println("所有人到终点，比赛结束。");
    }
}
