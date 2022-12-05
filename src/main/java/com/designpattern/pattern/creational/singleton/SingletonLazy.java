package com.designpattern.pattern.creational.singleton;

/**
 * @author miclefengzss
 * 2022/12/5 上午10:09
 *
 * 懒汉式，支持延迟加载，synchronized加载静态方法上，相当于锁定整个类，频繁的加锁、释放锁，导致并发度低，影响性能
 */
public class SingletonLazy {

    private SingletonLazy() {

    }

    private static SingletonLazy instance;

    public static synchronized SingletonLazy getInstance() {
        if (instance == null) {
            instance = new SingletonLazy();
        }
        return instance;
    }
}
