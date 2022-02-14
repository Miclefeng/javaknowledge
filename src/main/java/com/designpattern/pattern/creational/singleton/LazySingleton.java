package com.designpattern.pattern.creational.singleton;

/**
 * @author miclefengzss
 * 2022/1/14 下午4:12
 */
public class LazySingleton {

    private static LazySingleton INSTANCE;

    private LazySingleton() {
        if (INSTANCE != null) {
            throw new RuntimeException("单列构造器禁止反射调用.");
        }
    }

    public synchronized static LazySingleton getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new LazySingleton();
        }
        return INSTANCE;
    }
}
