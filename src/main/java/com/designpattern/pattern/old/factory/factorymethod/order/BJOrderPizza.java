package com.designpattern.pattern.old.factory.factorymethod.order;

import com.designpattern.pattern.old.factory.factorymethod.pizza.BJCheesePizza;
import com.designpattern.pattern.old.factory.factorymethod.pizza.BJPepperPizza;
import com.designpattern.pattern.old.factory.factorymethod.pizza.Pizza;

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
