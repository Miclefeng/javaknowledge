package com.javase.thread.oldbase.threadpool;

import java.util.concurrent.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author miclefengzss
 * 2021/9/18 下午5:06
 * 演示每个任务执行前后放钩子函数
 */
public class PauseThreadPool extends ThreadPoolExecutor {

    /**
     * 暂停状态
     */
    private boolean isPaused;

    private final ReentrantLock lock = new ReentrantLock();

    private Condition unPaused = lock.newCondition();

    public PauseThreadPool(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
    }

    public PauseThreadPool(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory);
    }

    public PauseThreadPool(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, RejectedExecutionHandler handler) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, handler);
    }

    public PauseThreadPool(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory, RejectedExecutionHandler handler) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory, handler);
    }

    @Override
    protected void beforeExecute(Thread t, Runnable r) {
        super.beforeExecute(t, r);
        lock.lock();
        try {
            while (isPaused) {
                unPaused.await();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    private void pause() {
        lock.lock();
        try {
            isPaused = true;
        } finally {
            lock.unlock();
        }
    }


    private void resume() {
        lock.lock();
        try {
            isPaused = false;
            unPaused.signalAll();
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        final PauseThreadPool pauseThreadPool = new PauseThreadPool(10, 20, 10L, TimeUnit.SECONDS, new LinkedBlockingDeque<>());

        final Runnable runnable = () -> {
            System.out.println("我在执行");
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        for (int i=0;i < 10000; i++) {
            pauseThreadPool.execute(runnable);
        }

        try {
            Thread.sleep(1500);
            pauseThreadPool.pause();
            System.out.println("线程池被暂停了!");
            Thread.sleep(1500);
            pauseThreadPool.resume();
            System.out.println("线程池被恢复了!");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
