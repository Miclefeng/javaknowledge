package com.designpattern.pattern.structural.mooc.decorator;

/**
 * @author miclefengzss
 * 2022/4/8 下午4:52
 */
public class EggDecorator extends AbstractDecorator {

    public EggDecorator(AbstractBatterCake abstractBatterCake) {
        super(abstractBatterCake);
    }

    @Override
    protected String doSomething() {
        return null;
    }

    @Override
    protected String getDesc() {
        return super.getDesc() + " 加一个鸡蛋";
    }

    @Override
    protected int cost() {
        return super.cost() + 2;
    }
}
