package com.thread.base.stopthreads;

/**
 * @author miclefengzss
 */
public class CantInterrupt {

    public static void main(String[] args) throws InterruptedException {
        Runnable runnable = () -> {
            int num = 0;
            while (num <= 1000 && !Thread.currentThread().isInterrupted()) {
                if (num % 100 == 0) {
                    System.out.println(num + " 是100的倍数.");
                }
                num++;

                try {
                    Thread.sleep(10);
                    // 线程响应中断后，会清除 interrupt 的状态，isInterrupted 的状态就没有了
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        Thread thread = new Thread(runnable);
        thread.start();

        Thread.sleep(5000);
        thread.interrupt();
    }
}
