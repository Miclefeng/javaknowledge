package com.thread.base.threadobjectcommonmethods;

/**
 * join 等待子线程执行完毕，在执行主线程后续程序
 */
public class ThreadJoin implements Runnable {

    public static void main(String[] args) {
        ThreadJoin r = new ThreadJoin();
        Thread t1 = new Thread(r);
        Thread t2 = new Thread(r);
        t1.start();
        t2.start();
        System.out.println("开始执行子线程.");
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("所有子线程执行完毕.");
    }

    @Override
    public void run() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " 运行结束.");
    }
}
