package com.designpattern.pattern.old.factory.factorymethod;

import com.designpattern.pattern.old.factory.factorymethod.order.BJOrderPizza;

/**
 * 工厂方法模式
 * 定义一个创建对象的抽象方法，由子类决定要实例化的类。
 * 将对象的实例化推迟到子类中实现
 * @author miclefengzss
 */
public class PizzaStore {

    public static void main(String[] args) {
        new BJOrderPizza();
    }
}
