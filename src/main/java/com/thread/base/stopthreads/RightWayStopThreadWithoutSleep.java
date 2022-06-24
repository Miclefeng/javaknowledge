package com.thread.base.stopthreads;

/**
 * 通过 Interrupt 通知线程，进行停止
 */
public class RightWayStopThreadWithoutSleep implements Runnable {

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new RightWayStopThreadWithoutSleep());
        thread.start();

        Thread.sleep(1000);
        thread.interrupt();
    }

    @Override
    public void run() {
        int num = 0;
        // 判断线程是否被 interrupted ，如果是则终止线程
        while (!Thread.currentThread().isInterrupted() && num <= Integer.MAX_VALUE / 2) {
            if (num % 10000 == 0) {
                System.out.println(num + " 是10000的倍数.");
            }
            num++;
        }
        System.out.println("执行完毕.");
    }
}
