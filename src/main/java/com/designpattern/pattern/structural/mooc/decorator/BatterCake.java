package com.designpattern.pattern.structural.mooc.decorator;

/**
 * @author miclefengzss
 * 2022/4/8 下午4:06
 */
public class BatterCake extends AbstractBatterCake {
    @Override
    protected String getDesc() {
        return "煎饼";
    }

    @Override
    protected int cost() {
        return 8;
    }
}
