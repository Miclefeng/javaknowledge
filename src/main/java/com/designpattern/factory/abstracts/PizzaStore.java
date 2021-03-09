package com.designpattern.factory.abstracts;

import com.designpattern.factory.abstracts.order.BJFactory;
import com.designpattern.factory.abstracts.order.OrderPizza;

/**
 * @author miclefengzss
 */
public class PizzaStore {

    public static void main(String[] args) {
        new OrderPizza(new BJFactory());
    }
}
