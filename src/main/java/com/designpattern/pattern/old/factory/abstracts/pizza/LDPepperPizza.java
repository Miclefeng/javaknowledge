package com.designpattern.pattern.old.factory.abstracts.pizza;

/**
 * @author miclefengzss
 */
public class LDPepperPizza extends Pizza {

    @Override
    public void prepare() {
        String name = "LD pepper pizza";
        setName(name);
        System.out.println(name + " start prepare.");
    }
}
