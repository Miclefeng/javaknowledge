package com.javase.thread.base;

/**
 * @author miclefengzss
 * 2022/12/27 下午1:50
 */
public class NoVisibility {

    /**
     * ready 没有保证可见性，需要加 volatile
     */
    private static volatile boolean ready = false;

    private static int number;

    private static class ReaderThread implements Runnable {

        @Override
        public void run() {
            while (!ready) {
                Thread.yield();
            }

            // number 输出可能为0，因为有可能 ready = true 和 number = 42 的指令顺序会被重排序。
            System.out.println(number);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(new ReaderThread());
        t.start();

        number = 42;
        ready = true;

        t.join();
    }
}
