package com.designpattern.pattern.creational.singleton;

import java.io.ObjectStreamException;

/**
 * @author miclefengzss
 * 2022/12/5 上午10:17
 * <p>
 * 静态内部类，根据静态内部类特性(内部类只有被使用的时候才被加载，外部类不影响内部类)，线程安全(JVM 类加载实现)，支持延迟加载
 */
public class SingletonStaticInnerClass {

    private SingletonStaticInnerClass() {
        // 防止反射破坏
        if (StaticInnerClass.instance != null) {
            throw new RuntimeException("实例已经存在，请通过 getInstance()方法获取");
        }
    }

    public static SingletonStaticInnerClass getInstance() {
        return StaticInnerClass.instance;
    }

    static class StaticInnerClass {
        static SingletonStaticInnerClass instance = new SingletonStaticInnerClass();
    }

    /**
     * 如果非要实现序列化接口，重写 readResolve(),反序列化时直接返回单例相关对象
     *
     * @return
     * @throws ObjectStreamException
     */
    public Object readResolve() throws ObjectStreamException {
        return StaticInnerClass.instance;
    }
}
