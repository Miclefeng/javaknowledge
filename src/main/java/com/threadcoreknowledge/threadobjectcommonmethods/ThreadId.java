package com.threadcoreknowledge.threadobjectcommonmethods;

public class ThreadId {

    public static void main(String[] args) {
        System.out.println("主线程的Id是：" + Thread.currentThread().getId());
        System.out.println("子线程的Id是：" + (new Thread().getId()));
    }
}
