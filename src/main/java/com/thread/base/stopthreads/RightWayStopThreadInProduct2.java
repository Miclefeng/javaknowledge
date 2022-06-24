package com.thread.base.stopthreads;

/**
 * 如果不想或者无法传递 InterruptedException ，那么应该在 catch 子句中调用
 * Thread.currentThread().interrupt() 来恢复设置中断状态，以便于在后续的执行依然能够
 * 检测到刚才放生的中断
 * @author miclefengzss
 */
public class RightWayStopThreadInProduct2 implements Runnable {

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new RightWayStopThreadInProduct2());
        thread.start();
        Thread.sleep(1000);
        thread.interrupt();
    }

    @Override
    public void run() {
        while (true) {
            if (Thread.currentThread().isInterrupted()) {
                System.out.println("Interrupted, 程序运行结束");
                break;
            }
            reInterrupt();
        }
    }

    public void reInterrupt() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            e.printStackTrace();
        }
    }
}
