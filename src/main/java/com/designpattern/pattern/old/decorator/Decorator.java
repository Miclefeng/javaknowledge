package com.designpattern.pattern.old.decorator;

/**
 * 装饰者模式
 * 动态的将新功能附加到对象上
 * 通过组合的方式将新功能附加到对象上
 *
 * @author miclefengzss
 */
public class Decorator extends Drink {

    private Drink drink;

    public Decorator(Drink drink) {
        this.drink = drink;
    }

    @Override
    public String getDescription() {
        return super.getDescription() + " price: " + super.getPrice() + " && " + drink.getDescription();
    }

    @Override
    public float coast() {
        return super.getPrice() + drink.coast();
    }
}
