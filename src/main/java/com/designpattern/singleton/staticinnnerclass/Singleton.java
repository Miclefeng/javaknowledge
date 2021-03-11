package com.designpattern.singleton.staticinnnerclass;

/**
 * 静态内部类
 *
 * 静态内部类在 外部类被装载的时候不会立即实例化，而是在调用getInstance的时候
 * 才会装载内部类，进而实现外部类的实例化
 *
 * 类的静态属性只会在第一次加载类的时候才会初始化，所以是线程安全的
 * @author miclefengzss
 */
public class Singleton {

    private Singleton() {

    }

    static class InnerClassSingleton {
        public final static Singleton INSTANCE = new Singleton();
    }

    public static Singleton getInstance() {
        return InnerClassSingleton.INSTANCE;
    }
}
