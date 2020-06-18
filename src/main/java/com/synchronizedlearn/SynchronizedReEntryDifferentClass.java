package com.synchronizedlearn;

/**
 * synchronized 修饰的不同类是可重入的
 * synchronized 的锁是线程级别的
 */
public class SynchronizedReEntryDifferentClass {

    public synchronized void doSomething() {
        System.out.println("我是父类的方法.");
    }
}

class SubspendClass extends SynchronizedReEntryDifferentClass {

    public synchronized void doSomething() {
        System.out.println("我是子类的方法.");
        super.doSomething();
    }

    public static void main(String[] args) {
        SubspendClass s = new SubspendClass();
        s.doSomething();
    }
}
