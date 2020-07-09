package com.synchronizedlearn;

/**
 * synchronized 修饰的不同类是可重入的
 * synchronized 的锁是线程级别的
 * 不可中断： 一旦 synchronized 的锁被别的线程所获取，其它线程必须等待或者阻塞，直到别的线程释放锁
 */
public class SynchronizedReEntryDifferentClass {

    public synchronized void doSomething() {
        System.out.println("我是父类的方法.");
    }
}

class SubspendClass extends SynchronizedReEntryDifferentClass {

    @Override
    public synchronized void doSomething() {
        System.out.println("我是子类的方法.");
        super.doSomething();
    }

    public static void main(String[] args) {
        SubspendClass s = new SubspendClass();
        s.doSomething();
    }
}
