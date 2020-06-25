package com.basic.knowledge.designpattern.reflect;

public class HeroFactory {

    public static ISkill getHero(String name) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        // 反射、元类
        // 类是对 对象的抽象
        // 元类是对类的描述
        // 反射需要带有完整包路径的类名
        String classPackage = "com.basic.knowledge.designpattern.reflect.hero." + name;
        Class<?> cla = Class.forName(classPackage);
        Object object = cla.newInstance();
        return (ISkill) object;
    }
}
