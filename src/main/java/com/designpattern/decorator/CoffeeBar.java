package com.designpattern.decorator;

/**
 * @author miclefengzss
 */
public class CoffeeBar {

    public static void main(String[] args) {

        Drink coffee = new Espresso();
        System.out.println(coffee.getDescription());
        System.out.println(coffee.coast());
        System.out.println("=======================");
        coffee = new Milk(coffee);
        System.out.println(coffee.getDescription());
        System.out.println(coffee.coast());
        System.out.println("=======================");
        coffee = new Chocolate(coffee);
        System.out.println(coffee.getDescription());
        System.out.println(coffee.coast());
    }
}
