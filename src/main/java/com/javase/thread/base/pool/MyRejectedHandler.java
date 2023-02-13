package com.javase.thread.base.pool;

import java.util.concurrent.*;

/**
 * @author miclefengzss
 * 2023/2/6 上午10:55
 */
public class MyRejectedHandler {

    public static void main(String[] args) {
        ExecutorService service = new ThreadPoolExecutor(
                4,  // 核心线程数
                4, // 最大线程数
                0, // 生命周期，空闲线程的生存时间
                TimeUnit.SECONDS, // 生命周期的时间格式
                new ArrayBlockingQueue<>(100), // 等待队列
                Executors.defaultThreadFactory(), // 线程工厂
                new MyHandler() // 拒绝策略
        );
    }

    static class MyHandler implements RejectedExecutionHandler {

        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
            // log("r rejected")

            // try 3 times
            if (executor.getQueue().size() < 100) {
                // try put again
                try {
                    executor.getQueue().put(r);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            // save r mysql mq
        }
    }
}
