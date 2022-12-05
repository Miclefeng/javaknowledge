package com.designpattern.pattern.creational.mooc.singleton;

/**
 * @author miclefengzss
 * 2022/1/14 下午5:33
 */
public class StaticInnerClassSingleton {

    private static class InnerClass {
        private static StaticInnerClassSingleton INSTANCE = new StaticInnerClassSingleton();
    }

    private StaticInnerClassSingleton() {
        if (InnerClass.INSTANCE != null) {
            throw new RuntimeException("单列模式禁止反射构造。");
        }
    }

    public static StaticInnerClassSingleton getInstance() {
        return InnerClass.INSTANCE;
    }
}
