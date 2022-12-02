package com.javase.thread.base.threadpool;

/**
 * @author miclefengzss
 * 2021/9/18 上午11:34
 */

/**
 * 1、反复创建线程的开销大
 * 2、过多的线程会占用太多的内存
 *
 * 线程池优点:
 * 1、加快响应速度
 * 2、合理利用CPU和内存
 * 3、统一管理资源
 */
public class EveryTaskOneThread {

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(new Task());
            thread.start();
        }
    }

    static class Task implements Runnable {
        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + ": 执行力任务");
        }
    }
}
