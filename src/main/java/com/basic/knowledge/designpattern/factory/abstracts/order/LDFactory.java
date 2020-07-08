package com.basic.knowledge.designpattern.factory.abstracts.order;

import com.basic.knowledge.designpattern.factory.abstracts.pizza.LDCheesePizza;
import com.basic.knowledge.designpattern.factory.abstracts.pizza.LDPepperPizza;
import com.basic.knowledge.designpattern.factory.abstracts.pizza.Pizza;

/**
 * @author miclefengzss
 */
public class LDFactory implements AbstractFactory {

    @Override
    public Pizza createPizza(String orderType) {
        Pizza pizza = null;
        if ("cheese".equals(orderType)) {
            pizza = new LDCheesePizza();
        } else if ("pepper".equals(orderType)) {
            pizza = new LDPepperPizza();
        }
        return pizza;
    }
}
