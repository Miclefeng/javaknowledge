package com.basic.knowledge.designpattern.singleton.enumerate;

/**
 * 枚举方式实现单例可以避免多线程同步问题，还能防止
 * 反序列化重新创建新的对象
 * @author miclefengzss
 */
public class EnumSingleton {

    public static void main(String[] args) {
        Singleton instance = Singleton.INSTANCE;
        Singleton instance2 = Singleton.INSTANCE;
        System.out.println(instance == instance2);
        instance.sayok();
    }
}

enum Singleton {
    INSTANCE;
    public void sayok() {
        System.out.println("ok~.");
    }
}
