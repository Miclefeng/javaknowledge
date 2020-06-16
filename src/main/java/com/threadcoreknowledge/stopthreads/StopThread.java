package com.threadcoreknowledge.stopthreads;

/**
 * 错误的停止方法：
 * 用 stop() 来停止线程，会导致线程运行一半突然停止，没办法完成一个基本单位的操作，
 * 会造成脏数据.
 */
public class StopThread implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println("连队 " + i + " 开始领取武器");
            for (int j = 0; j < 10; j++) {
                System.out.println(j);
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("连队 " + i + "已经领取完毕.");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new StopThread());
        thread.start();
        Thread.sleep(1060);
        thread.stop();
    }
}
