package com.designpattern.pattern.creational.mooc.singleton;

import java.io.Serializable;

/**
 * @author miclefengzss
 * 2022/1/14 下午5:40
 */
public class HungrySingleton implements Serializable, Cloneable {

    private static HungrySingleton INSTANCE;

    static {
        INSTANCE = new HungrySingleton();
    }

    private HungrySingleton() {
        if (INSTANCE != null) {
            throw new RuntimeException("单列模式禁止反射构造。");
        }
    }

    public static HungrySingleton getInstance() {
        return INSTANCE;
    }

    /**
     * 防止反序列化返回不同的实例
     *
     * @return
     */
    private Object readResolve() {
        return INSTANCE;
    }

    /**
     * 防止克隆时产生新的实例
     *
     * @return
     * @throws CloneNotSupportedException
     */
    @Override
    protected Object clone() throws CloneNotSupportedException {
        return getInstance();
    }
}
