package com.javase.thread.base.stopthreads;

/**
 * 处理中断最好的方式
 * 优先选择在方法上抛出异常，以便于将异常传递到顶层。让 run 方法可以捕获这一异常。
 * 由于 run 方法中无法抛出 checked Exception(只能用 try/catch),顶层方法必须处理该异常。
 * <p>
 * java 中响应中断的方法
 * 1、Object.wait()/wait(long)/wait(long,int)
 * 2、Thread.sleep(long)/sleep(long,int)
 * 3、Thread.join()/join(long)/join(long,int)
 * 4、java.util.concurrent.BlockingQueue.take()/put(E)
 * 5、java.util.concurrent.locks.Lock.lockInterruptibly()
 * 6、java.util.concurrent.CountDownLatch.await()
 * 7、java.util.concurrent.CyclicBarrier.await()
 * 8、java.util.concurrent.Exchanger.exchange(V)
 * 9、java.nio.channels.InterruptibleChannel相关方法
 * 10、java.nio.channels.Selector的相关方法
 * @author miclefengzss
 */
public class RightWayStopThreadInProduct implements Runnable {

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new RightWayStopThreadInProduct());
        thread.start();
        Thread.sleep(1000);
        thread.interrupt();
    }

    @Override
    public void run() {
        while (true && !Thread.currentThread().isInterrupted()) {
            System.out.println("go");
            try {
                throwInMethod();
            } catch (InterruptedException e) {
                System.out.println("保存日志/终止程序");
                e.printStackTrace();
                break;
            }
        }
    }

    public void throwInMethod() throws InterruptedException {
        Thread.sleep(2000);
    }
}
