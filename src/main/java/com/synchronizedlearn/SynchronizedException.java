package com.synchronizedlearn;

public class SynchronizedException implements Runnable {

    public static SynchronizedException instance = new SynchronizedException();

    public static void main(String[] args) {

        /**
         * 同一个对象的不同方法，有一个方法抛出exception不会阻塞另一个方法，抛出异常后 jvm 会自动释放锁
         */
        Thread t1 = new Thread(instance);
        Thread t2 = new Thread(instance);
        t1.start();
        t2.start();
        while (t1.isAlive() || t2.isAlive()) {

        }
        System.out.println("Finished.");
    }

    @Override
    public void run() {
        if (Thread.currentThread().getName().equals("Thread-0")) {
            method1();
        } else {
            method2();
        }
    }

    public synchronized void method1() {
        System.out.println("我是Exception Method：" + Thread.currentThread().getName());
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        throw new RuntimeException();
    }

    public synchronized void method2() {
        System.out.println("我是normal Method：" + Thread.currentThread().getName());
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "运行结束.");
    }
}
