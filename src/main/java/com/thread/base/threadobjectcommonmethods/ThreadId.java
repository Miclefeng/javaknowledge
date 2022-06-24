package com.thread.base.threadobjectcommonmethods;

/**
 * Id 从1开始，JVM运行起来后会创建和系统相关的线程，所以我们创建的子线程的Id早已不是2.
 */
public class ThreadId {

    public static void main(String[] args) {
        System.out.println("主线程的Id是：" + Thread.currentThread().getId());
        System.out.println("子线程的Id是：" + (new Thread().getId()));
    }
}
