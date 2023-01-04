package com.javase.thread.oldbase.threadobjectcommonmethods;

/**
 * 展示线程sleep的时候不释放synchronized的monitor锁，等待sleep时间到了以后，正常结束才释放锁
 */
public class ThreadSleepDontReleaseMonitor implements Runnable {

    public static void main(String[] args) {
        ThreadSleepDontReleaseMonitor r = new ThreadSleepDontReleaseMonitor();
        new Thread(r).start();
        new Thread(r).start();
    }

    @Override
    public void run() {
        sync();
    }

    private synchronized void sync() {
        System.out.println(Thread.currentThread().getName() + " 获取到了monitor.");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " 退出了同步代码块.");
    }
}
