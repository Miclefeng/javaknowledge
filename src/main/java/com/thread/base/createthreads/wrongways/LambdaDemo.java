package com.thread.base.createthreads.wrongways;

/**
 * 通过 lambda 表达式创建线程
 * @author miclefengzss
 */
public class LambdaDemo {

    public static void main(String[] args) {
        new Thread(() -> System.out.println(Thread.currentThread().getName())).start();
    }
}
