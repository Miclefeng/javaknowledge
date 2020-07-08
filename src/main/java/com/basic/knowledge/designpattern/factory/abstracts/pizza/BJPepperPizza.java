package com.basic.knowledge.designpattern.factory.abstracts.pizza;

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
