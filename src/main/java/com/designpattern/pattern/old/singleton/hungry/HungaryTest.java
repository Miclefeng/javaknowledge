package com.designpattern.pattern.old.singleton.hungry;

/**
 * @author miclefengzss
 */
public class HungaryTest {

    public static void main(String[] args) {
        HungryStaticValue instance1 = HungryStaticValue.getInstance();
        HungryStaticValue instance2 = HungryStaticValue.getInstance();
        System.out.println(instance1 == instance2);

        HungryStaticBlock instance3 = HungryStaticBlock.getInstance();
        HungryStaticBlock instance4 = HungryStaticBlock.getInstance();
        System.out.println(instance3 == instance4);
    }
}
