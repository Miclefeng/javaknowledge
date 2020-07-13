package com.basic.knowledge.designpattern.decorator;

/**
 * @author miclefengzss
 */
public class Coffee extends Drink {

    @Override
    public float coast() {
        return super.getPrice();
    }
}
