package com.basic.knowledge.designpattern.factory.abstracts.order;

import com.basic.knowledge.designpattern.factory.abstracts.pizza.Pizza;

/**
 * @author miclefengzss
 */
public interface AbstractFactory {

    Pizza createPizza(String orderType);
}
