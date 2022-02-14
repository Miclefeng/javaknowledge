package com.designpattern.pattern.old.factory.factorymethod.pizza;

/**
 * @author miclefengzss
 */
public class BJPepperPizza extends Pizza {

    @Override
    public void prepare() {
        String name = "BJ pepper pizza";
        setName(name);
        System.out.println(name + " start prepare.");
    }
}
