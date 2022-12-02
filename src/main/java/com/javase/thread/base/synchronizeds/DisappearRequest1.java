package com.javase.thread.base.synchronizeds;

public class DisappearRequest1 implements Runnable {

    public static DisappearRequest1 instance = new DisappearRequest1();

    public static int i = 0;

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(instance);
        Thread t2 = new Thread(instance);
        t1.start();
        t2.start();

        t1.join();
        t2.join();
        System.out.println(i);
    }

    @Override
    public synchronized void run() {
        for (int j = 0; j < 100000; j++) {
            /**
             * i++ 实际上分为3步
             * 1、在内存中读取 i 的值
             * 2、对 i 的值进行 +1
             * 3、将 i 的值写入到内存中
             */
            i++;
        }
    }
}
