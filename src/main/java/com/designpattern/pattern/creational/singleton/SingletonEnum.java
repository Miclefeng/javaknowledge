package com.designpattern.pattern.creational.singleton;

/**
 * @author miclefengzss
 * 2022/12/5 上午10:23
 *
 * 枚举类型是线程安全的，并且只会装载一次，不会被反射和序列化破坏(枚举类没有无参构造，不允许通过反射获取)
 */
public class SingletonEnum {

    private SingletonEnum() {

    }

    public static SingletonEnum getInstance() {
        return InnerEnum.INSTANCE.instance;
    }

    private enum InnerEnum {
        INSTANCE;

        private final SingletonEnum instance;

        InnerEnum() {
            this.instance = new SingletonEnum();
        }
    }
}


