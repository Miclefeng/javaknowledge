package com.designpattern.pattern.creational.mooc.singleton;

/**
 * @author miclefengzss
 * 2022/1/14 下午4:37
 */
public class LazyDoubleCheckSingleton {

    // 1.分配内存给这个对象
    // 3.设置lazyDoubleCheckSingleton 指向刚分配的内存地址
    // 2.初始化对象
    // intra-thread semantics (允许那些在单线程内，不会改变单线程程序执行结果的重排序。)
    // 3.设置lazyDoubleCheckSingleton 指向刚分配的内存地址
    private volatile static LazyDoubleCheckSingleton INSTANCE;

    private LazyDoubleCheckSingleton() {

    }

    public static LazyDoubleCheckSingleton getInstance() {
        if (INSTANCE == null) {
            synchronized (LazyDoubleCheckSingleton.class) {
                if (INSTANCE == null) {
                    INSTANCE = new LazyDoubleCheckSingleton();
                }
            }
        }
        return INSTANCE;
    }
}
