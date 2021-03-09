package com.designpattern.singleton.hungry;

/**
 * @author miclefengzss
 */
public class HungryStaticBlock {

    private static final HungryStaticBlock instance;

    static {
        instance = new HungryStaticBlock();
    }

    private HungryStaticBlock() {

    }

    public static HungryStaticBlock getInstance() {
        return instance;
    }
}
