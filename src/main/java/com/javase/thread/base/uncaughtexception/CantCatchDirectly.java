package com.javase.thread.base.uncaughtexception;

/**
 * 执行时发现，根本没有Caught Exception，线程234依然运行并且抛出异常
 * 子线程的异常不能用传统方法捕获
 */
public class CantCatchDirectly implements Runnable {

    public static void main(String[] args) {
        try {
            CantCatchDirectly r = new CantCatchDirectly();
            new Thread(r, "线程1").start();
            new Thread(r, "线程2").start();
            new Thread(r, "线程3").start();
            new Thread(r, "线程4").start();
        } catch (RuntimeException e) {
            System.out.println("Caught 子线程的异常.");
        }
    }

    @Override
    public void run() {
        try {
            // 手动在 run 方法抛出异常，不推荐使用
            throw new RuntimeException();
        } catch (RuntimeException e) {
            System.out.println(Thread.currentThread().getName() + ", Caught Exception.");
        }
    }
}
