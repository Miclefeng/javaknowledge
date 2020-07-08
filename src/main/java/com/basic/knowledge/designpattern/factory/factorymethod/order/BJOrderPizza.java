package com.basic.knowledge.designpattern.factory.factorymethod.order;

import com.basic.knowledge.designpattern.factory.factorymethod.pizza.BJCheesePizza;
import com.basic.knowledge.designpattern.factory.factorymethod.pizza.BJPepperPizza;
import com.basic.knowledge.designpattern.factory.factorymethod.pizza.Pizza;

/**
 * @author miclefengzss
 */
public class BJOrderPizza extends OrderPizza {

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
