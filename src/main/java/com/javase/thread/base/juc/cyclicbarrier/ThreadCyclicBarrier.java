package com.javase.thread.base.juc.cyclicbarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @author miclefengzss
 * 2021/12/6 下午4:53
 */
public class ThreadCyclicBarrier {

    /**
     * CyclicBarrier要等固定数量的线程数都到达了栅栏位置才能继续执行
     * CyclicBarrier用于线程
     *
     * @param args
     */
    public static void main(String[] args) {

        CyclicBarrier cyclicBarrier = new CyclicBarrier(5, () -> {
            System.out.println("所有人都到齐了，准备出发");
        });

        for (int i = 0; i < 10; i++) {
            new Thread(new Tasks(i + 1, cyclicBarrier)).start();
        }
    }

    static class Tasks implements Runnable {
        private int id;
        private CyclicBarrier cyclicBarrier;

        public Tasks(int id, CyclicBarrier cyclicBarrier) {
            this.id = id;
            this.cyclicBarrier = cyclicBarrier;
        }

        @Override
        public void run() {
            System.out.println("线程[" + id + "] 现在前往集合地点。");
            try {
                Thread.sleep((long) (Math.random() * 10000));
                System.out.println("线程[" + id + "] 到达了集合地点，等待其他人到达");
                cyclicBarrier.await();
                System.out.println("线程[" + id + "] 出发了。");
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }
}
