package com.threadcoreknowledge.threadlifecycle;

/**
 * blocked  waiting  timed_waiting 3种状态
 * @author miclefengzss
 */
public class BlockedWaitingTimedWaiting implements Runnable {

    public static void main(String[] args) {
        BlockedWaitingTimedWaiting runnable = new BlockedWaitingTimedWaiting();
        Thread t1 = new Thread(runnable);
        Thread t2 = new Thread(runnable);
        t1.start();
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // timed_waiting 状态，因为正在执行 Thread.sleep(1000)
        System.out.println(t1.getState());
        t2.start();
        // blocked 状态，因为 t2 无法拿到 synchronized 锁导致
        System.out.println(t2.getState());

        try {
            Thread.sleep(1300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // waiting 状态，因为 t1 执行 wait()
        System.out.println(t1.getState());
    }

    @Override
    public void run() {
        sync();
    }

    private synchronized void sync() {
        try {
            Thread.sleep(1000);
            // t1正在执行的wait()方法会释放锁资源，从而t2可以拿到锁，正在sleep，所以是TIMED_WAITING
            wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
