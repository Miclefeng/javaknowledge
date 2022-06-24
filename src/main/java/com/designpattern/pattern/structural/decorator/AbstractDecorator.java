package com.designpattern.pattern.structural.decorator;

/**
 * @author miclefengzss
 * 2022/4/8 下午4:50
 */
public abstract class AbstractDecorator extends AbstractBatterCake {

    private AbstractBatterCake abstractBatterCake;

    public AbstractDecorator(AbstractBatterCake abstractBatterCake) {
        this.abstractBatterCake = abstractBatterCake;
    }

    protected abstract String doSomething();

    @Override
    protected String getDesc() {
        return this.abstractBatterCake.getDesc();
    }

    @Override
    protected int cost() {
        return this.abstractBatterCake.cost();
    }
}
