package com.designpattern.factory.abstracts.pizza;

/**
 * @author miclefengzss
 */
public class LDCheesePizza extends Pizza {

    @Override
    public void prepare() {
        String name = "LD cheese pizza";
        setName(name);
        System.out.println(name + " start prepare.");
    }
}
