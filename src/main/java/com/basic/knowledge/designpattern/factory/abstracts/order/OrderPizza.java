package com.basic.knowledge.designpattern.factory.abstracts.order;

import com.basic.knowledge.designpattern.factory.abstracts.pizza.Pizza;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
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
