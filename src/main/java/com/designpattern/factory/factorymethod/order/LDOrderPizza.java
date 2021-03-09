package com.designpattern.factory.factorymethod.order;

import com.designpattern.factory.factorymethod.pizza.LDCheesePizza;
import com.designpattern.factory.factorymethod.pizza.Pizza;

/**
 * @author miclefengzss
 */
public class LDOrderPizza extends OrderPizza {

    @Override
    public Pizza createPizza(String orderType) {
        Pizza pizza = null;
        if (orderType.equals("cheese")) {
            pizza = new LDCheesePizza();
        } else if (orderType.equals("pepper")) {
            pizza = new LDCheesePizza();
        }

        return pizza;
    }
}
