package com.designpattern.pattern.creational.singleton;

/**
 * @author miclefengzss
 * 2022/12/5 上午10:06
 *
 * 饿汉式，线程安全，不支持延迟加载，如果对象过大而又没被使用造成内存浪费
 */
public class SingletonHungry {

    private SingletonHungry() {

    }

    public static SingletonHungry instance = new SingletonHungry();

    public static SingletonHungry getInstance() {
        return instance;
    }
}
