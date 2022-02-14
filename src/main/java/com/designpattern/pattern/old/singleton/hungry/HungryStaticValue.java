package com.designpattern.pattern.old.singleton.hungry;

/**
 * 静态常量
 * 有点：在类装载的时候就实例化，避免了线程同步问题
 * 缺点：在类装载的时候就实例化，没有达到Lazy Loading的效果，如果没有使用过这个实例
 * 会造成内存浪费
 * @author miclefengzss
 */
public class HungryStaticValue {

    private final static HungryStaticValue instance = new HungryStaticValue();

    private HungryStaticValue() {

    }

    public static HungryStaticValue getInstance() {
        return instance;
    }
}
