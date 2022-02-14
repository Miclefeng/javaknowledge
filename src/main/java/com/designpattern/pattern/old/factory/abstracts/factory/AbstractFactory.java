package com.designpattern.pattern.old.factory.abstracts.factory;

import com.designpattern.pattern.old.factory.abstracts.pizza.Pizza;

/**
 * @author miclefengzss
 */
public interface AbstractFactory {

    Pizza createPizza(String orderType);
}
