package com.designpattern.factory.abstracts.order;

import com.designpattern.factory.abstracts.pizza.Pizza;

/**
 * @author miclefengzss
 */
public interface AbstractFactory {

    Pizza createPizza(String orderType);
}
