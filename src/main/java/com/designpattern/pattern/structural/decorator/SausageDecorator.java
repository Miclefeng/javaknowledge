package com.designpattern.pattern.structural.decorator;

/**
 * @author miclefengzss
 * 2022/4/8 下午4:54
 */
public class SausageDecorator extends AbstractDecorator {

    public SausageDecorator(AbstractBatterCake abstractBatterCake) {
        super(abstractBatterCake);
    }

    @Override
    protected String doSomething() {
        return null;
    }

    @Override
    protected String getDesc() {
        return super.getDesc() + " 加一根火腿";
    }

    @Override
    protected int cost() {
        return super.cost() + 4;
    }
}
