package com.designpattern.pattern.old.decorator;

/**
 * @author miclefengzss
 */
public class Coffee extends Drink {

    @Override
    public float coast() {
        return super.getPrice();
    }
}
