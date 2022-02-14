package com.designpattern.pattern.old.bridge;

/**
 * @author miclefengzss
 */
public class Xiaomi implements PhoneBrand {

    @Override
    public void open() {
        System.out.println(" xiaomi open.");
    }

    @Override
    public void call() {
        System.out.println(" xiaomi call.");
    }

    @Override
    public void close() {
        System.out.println(" xiaomi close.");
    }
}
