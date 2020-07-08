package com.basic.knowledge.designpattern.factory.abstracts.order;

import com.basic.knowledge.designpattern.factory.abstracts.pizza.BJCheesePizza;
import com.basic.knowledge.designpattern.factory.abstracts.pizza.BJPepperPizza;
import com.basic.knowledge.designpattern.factory.abstracts.pizza.Pizza;

/**
 * @author miclefengzss
 */
public class BJFactory implements AbstractFactory {

    @Override
    public Pizza createPizza(String orderType) {
        Pizza pizza = null;
        if (orderType.equals("cheese")) {
            pizza = new BJCheesePizza();
        } else if (orderType.equals("pepper")) {
            pizza = new BJPepperPizza();
        }
        return pizza;
    }
}
