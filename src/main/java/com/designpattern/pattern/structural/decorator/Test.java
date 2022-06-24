package com.designpattern.pattern.structural.decorator;

/**
 * @author miclefengzss
 * 2022/4/8 下午4:54
 */
public class Test {

    public static void main(String[] args) {
        AbstractBatterCake abstractBatterCake;
        abstractBatterCake = new BatterCake();

        abstractBatterCake = new EggDecorator(abstractBatterCake);
        abstractBatterCake = new EggDecorator(abstractBatterCake);
        abstractBatterCake = new SausageDecorator(abstractBatterCake);

        System.out.println(abstractBatterCake.getDesc() + ", 销售价格: " + abstractBatterCake.cost());
    }
}
