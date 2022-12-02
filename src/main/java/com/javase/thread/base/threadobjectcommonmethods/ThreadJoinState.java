package com.javase.thread.base.threadobjectcommonmethods;

/**
 * join 时主线程的状态是 waiting
 */
public class ThreadJoinState {

    public static void main(String[] args) {
        Thread mainThread = Thread.currentThread();
        Thread t1 = new Thread(() -> {
            try {
                Thread.sleep(1000);
                System.out.println(mainThread.getName() + " 线程状态是: " + mainThread.getState());
                System.out.println(Thread.currentThread().getName() + " 线程运行结束.");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        });

        t1.start();
        System.out.println("开始运行子线程.");
        try {
            t1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("子线程运行结束.");
    }
}
