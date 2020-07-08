package com.basic.knowledge.designpattern.singleton.lazy;

/**
 * 懒加载单例模式
 */
public class LazySingleton {

    /**
     * volatile 防止字节码重排序，从而避免多线程空指针引用问题。
     * 编译器(JIT)，CPU 可能对指令进行重排序，导致使用的实例尚未初始化，通过
     * volatile 进行修饰防止重排序
     */
    private volatile static LazySingleton instance;

    /**
     * 防止外部进行 new 实例化，导致单例失败
     */
    private LazySingleton() {

    }

    public static LazySingleton getInstance() {
        if (instance == null) {
            /**
             * 防止多线程重复实例化
             */
            synchronized (LazySingleton.class) {
                /**
                 * double check 进行加锁优化
                 * 在进行一次判断，防止多线程进入从而重复实例化
                 */
                if (instance == null) {
                    /**
                     * jvm 层 （分配空间始终在第一位，初始化和引用赋值可能为调换顺序（重排序）
                     * 多线程情况下有可能出现空指针引用问题）
                     * 1、 分配空间
                     * 2、 初始化
                     * 3、 引用赋值
                     */
                    instance = new LazySingleton();
                }
            }
        }

        return instance;
    }
}

class LazySingletonTest {

    public static void main(String[] args) {
        new Thread(()->{
            LazySingleton instance = LazySingleton.getInstance();
            System.out.println(instance);
        }).start();
        new Thread(()->{
            LazySingleton instance = LazySingleton.getInstance();
            System.out.println(instance);
        }).start();
    }
}
