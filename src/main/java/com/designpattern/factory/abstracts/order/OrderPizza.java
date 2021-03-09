package com.designpattern.factory.abstracts.order;

import com.designpattern.factory.abstracts.pizza.Pizza;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 工厂模式的意义
 * 将对象的实例化的代码提取出来，放到一个类里面统一管理和维护，达到和主项目的依赖关系解耦
 *
 * 设计模式依赖抽象原则
 *  1) 创建对象实例时，不要直接 new 类，而是把这个 new 类的动作放到一个工厂方法里面，并返回
 *  2) 不要让类继承具体的类，而是继承抽象类火灾实现interface
 *  3) 不要覆盖基类中已经实现的方法
 * @author miclefengzss
 */
public class OrderPizza {

    private AbstractFactory abstractFactory;

    public OrderPizza(AbstractFactory abstractFactory) {
        setAbstractFactory(abstractFactory);
    }

    private void setAbstractFactory(AbstractFactory abstractFactory) {
        // abstractFactory 是具体工厂类的实现
        this.abstractFactory = abstractFactory;

        Pizza pizza;
        do {
            String orderType = orderType();
            pizza = this.abstractFactory.createPizza(orderType);
            if (pizza != null) {
                pizza.prepare();
                pizza.bake();
                pizza.cut();
                pizza.box();
            } else {
                break;
            }
        } while (true);
    }

    private String orderType() {
        System.out.println("input pizza kind:");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String name = null;
        try {
            name = bufferedReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return name;
    }
}
