package com.designpattern.pattern.old.factory.abstracts;

import com.designpattern.pattern.old.factory.abstracts.factory.BJFactory;
import com.designpattern.pattern.old.factory.abstracts.order.OrderPizza;

/**
 * @author miclefengzss
 */
public class PizzaStore {

    public static void main(String[] args) {
        new OrderPizza(new BJFactory());
    }
}
