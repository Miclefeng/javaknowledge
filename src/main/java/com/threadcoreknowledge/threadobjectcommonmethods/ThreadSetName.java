package com.threadcoreknowledge.threadobjectcommonmethods;

public class ThreadSetName {

    public static void main(String[] args) {
        Thread t1 = new Thread();
        t1.start();
        t1.setName("线程1");
        System.out.println(t1.getName());
    }
}
