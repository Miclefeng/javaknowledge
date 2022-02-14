package com.designpattern.pattern.old.decorator;

/**
 * @author miclefengzss
 */
public class Milk extends Decorator {

    public Milk(Drink drink) {
        super(drink);
        setDescription(" add milk ");
        setPrice(1.0f);
    }
}
