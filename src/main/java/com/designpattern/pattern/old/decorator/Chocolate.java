package com.designpattern.pattern.old.decorator;

/**
 * @author miclefengzss
 */
public class Chocolate extends Decorator {

    public Chocolate(Drink drink) {
        super(drink);
        setDescription(" add chocolate");
        setPrice(2.0f);
    }
}
