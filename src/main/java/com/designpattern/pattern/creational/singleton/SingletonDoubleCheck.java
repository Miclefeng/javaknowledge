package com.designpattern.pattern.creational.singleton;

/**
 * @author miclefengzss
 * 2022/12/5 上午10:14
 *
 * 双重检查+volatile，线程安全，延迟加载，但编写复杂
 */
public class SingletonDoubleCheck {

    private static volatile SingletonDoubleCheck instance;

    private SingletonDoubleCheck() {

    }

    public static SingletonDoubleCheck getInstance() {
        if (instance == null) {
            synchronized (SingletonDoubleCheck.class) {
                if (instance == null) {
                    instance = new SingletonDoubleCheck();
                }
            }
        }
        return instance;
    }
}
