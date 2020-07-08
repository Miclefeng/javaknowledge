package com.basic.knowledge.designpattern.factory.abstracts.pizza;

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
