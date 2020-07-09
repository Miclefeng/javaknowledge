package com.threadcoreknowledge.stopthreads;

/**
 * @author miclefengzss
 */
public class RightWayStopThreadWithSleepEveryLoop {

    public static void main(String[] args) throws InterruptedException {
        Runnable runnable = () -> {
            int num = 0;

            try {
                while (num <= 3000) {
                    if (num % 100 == 0) {
                        System.out.println(num + " 是100的倍数.");
                    }
                    num++;
                    // 如果执行的循环中每次都有阻塞存在， 循环的判断条件中不需要判断线程是否被中断的状态
                    Thread.sleep(10);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        Thread thread = new Thread(runnable);
        thread.start();
        Thread.sleep(3000);
        thread.interrupt();
    }
}
