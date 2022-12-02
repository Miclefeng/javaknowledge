package com.javase.thread.base.threadpool;

/**
 * @author miclefengzss
 * 2021/9/18 上午11:40
 */

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 1、反复创建线程的开销大
 * 2、过多的线程会占用太多的内存
 * <p>
 * 线程池优点:
 * 1、加快响应速度
 * 2、合理利用CPU和内存
 * 3、统一管理资源
 */
public class CreateThreadPool {

    /**
     * 线程池构造方法的参数
     * corePooSize      核心线程数: 线程池在完成初始化之后默认是没有任何线程的，线程池会等待任务到来时，再创建新的线程去执行任务
     * maxPoolSize      最大线程数: 在核心线程数的基础上，在额外增加的线程数的上限
     * keepAliveTime    保持存活时间
     * workQueue        任务存储队列
     *          1、直接交换: SynchronousQueue        队列没有容量，直接消费掉
     *          2、无界队列: LinkedBlockingQueue     队列容量没有限制,容易造成大量内存占用，导致OOM
     *          3、有界队列: ArrayBlockingQueue      有容量的队列
     * threadFactory    当线程池需要新线程的时候，会使用threadFactory来生成新的线程
     * Handler          由于先陈词无法接受你所提交的任务的拒绝策略
     *          AbortPolicy:            抛出异常
     *          DiscardPolicy:          直接丢弃
     *          DiscardOldestPolicy:    丢弃最老的
     *          CallerRunsPolicy:       提交任务的线程去执行; 1、让主线程运行，避免损失，2、降低提交任务的速度，给线程池缓冲时间
     *
     * example:     Executors.newFixedThreadPool()          =>  LinkedBlockingQueue
     *              Executors.newSingleThreadExecutor()     =>  LinkedBlockingQueue
     *              Executors.newCachedThreadPool()         =>  SynchronousQueue;
     *                      CachedThreadPool 可缓存线程池: 具有自动回收多余线程的功能
     *                      弊端在于第二个参数maximumPoolSize被设置了Integer.MAX_VALUE,这可能创建非常多的线程，导致OOM
     *              Executors.newScheduledThreadPool()      =>
     *                      支持定时及周期性任务执行的线程池
     */

    public static void main(String[] args) {
//        final ExecutorService fixedThreadPool = Executors.newFixedThreadPool(4);
//        for (int i = 0; i < 1000; i++) {
//            fixedThreadPool.execute(new Task());
//        }

//        final ExecutorService singleThreadExecutor= Executors.newSingleThreadExecutor();
//        for (int i = 0; i < 1000; i++) {
//            singleThreadExecutor.execute(new Task());
//        }

//        final ExecutorService newCachedThreadPool = Executors.newCachedThreadPool();
//        for (int i = 0; i < 1000; i++) {
//            newCachedThreadPool.execute(new Task());
//        }

        final ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(10);
        scheduledExecutorService.schedule(new Task(), 5, TimeUnit.SECONDS);
        scheduledExecutorService.scheduleAtFixedRate(new Task(), 1, 3, TimeUnit.SECONDS);
    }

    static class Task implements Runnable {

        @Override
        public void run() {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName());
        }
    }
}
