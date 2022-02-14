package com.designpattern.pattern.creational.singleton;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * @author miclefengzss
 * 2022/1/17 下午10:17
 */
public class Test {

    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {

//        Class clazz = HungrySingleton.class;
        Class clazz = StaticInnerClassSingleton.class;

        Constructor constructor = clazz.getDeclaredConstructor();
        constructor.setAccessible(true);

        StaticInnerClassSingleton instance = StaticInnerClassSingleton.getInstance();
        StaticInnerClassSingleton newInstance = (StaticInnerClassSingleton) constructor.newInstance();

//        HungrySingleton instance = HungrySingleton.getInstance();
//        HungrySingleton newInstance = (HungrySingleton) constructor.newInstance();

        System.out.println(instance);
        System.out.println(newInstance);
        System.out.println(instance == newInstance);
    }
}
