package com.designpattern.pattern.creational.singleton;

/**
 * @author miclefengzss
 * 2022/12/5 上午10:20
 */
public class Test {

    public static void main(String[] args) {
        SingletonStaticInnerClass instance = SingletonStaticInnerClass.getInstance();
        SingletonStaticInnerClass instance2 = SingletonStaticInnerClass.getInstance();

        System.out.println(instance);
        System.out.println(instance2);


        SingletonEnum singletonEnum = SingletonEnum.getInstance();
        SingletonEnum singletonEnum2 = SingletonEnum.getInstance();
        System.out.println(singletonEnum);
        System.out.println(singletonEnum2);
    }
}
