package com.thread.base.stopthreads;

public class RightWayStopWithSleep {

    public static void main(String[] args) throws InterruptedException {
        Runnable runnable = () -> {
            int num = 0;
            try {
                while (num <= 3000 && !Thread.currentThread().isInterrupted()) {
                    if (num % 100 == 0) {
                        System.out.println(num + " 是100的倍数.");
                    }
                    num++;
                }

                // 如果线程阻塞，在接收到 interrupt 通知后会抛出 InterruptedException 的异常
                // 通过捕捉异常来终止线程
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        Thread thread = new Thread(runnable);
        thread.start();
        Thread.sleep(1000);
        thread.interrupt();
    }
}
