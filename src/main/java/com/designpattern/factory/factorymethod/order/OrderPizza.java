package com.designpattern.factory.factorymethod.order;

import com.designpattern.factory.factorymethod.pizza.Pizza;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author miclefengzss
 */
public abstract class OrderPizza {

    public OrderPizza() {
        Pizza pizza;
        String orderType;
        do {
            orderType = getType();
            // 抽象方法，由工厂子类完成
            pizza = createPizza(orderType);
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

    public abstract Pizza createPizza(String orderType);

    private String getType() {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("input pizza kind: ");
        String pizzaName = null;
        try {
            pizzaName = bufferedReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return pizzaName;
    }
}
