package com.designpattern.pattern.old.factory.abstracts.factory;

import com.designpattern.pattern.old.factory.abstracts.pizza.BJCheesePizza;
import com.designpattern.pattern.old.factory.abstracts.pizza.BJPepperPizza;
import com.designpattern.pattern.old.factory.abstracts.pizza.Pizza;

/**
 * @author miclefengzss
 */
public class BJFactory implements AbstractFactory {

    @Override
    public Pizza createPizza(String orderType) {
        Pizza pizza = null;
        if ("cheese".equals(orderType)) {
            pizza = new BJCheesePizza();
        } else if ("pepper".equals(orderType)) {
            pizza = new BJPepperPizza();
        }
        return pizza;
    }
}
