package com.designpattern.factory.factorymethod.pizza;

/**
 * @author miclefengzss
 */
public class BJCheesePizza extends Pizza {

    @Override
    public void prepare() {
        String name = "BJ cheese pizza";
        setName(name);
        System.out.println(name + " start prepare.");
    }
}
