package com.basic.knowledge.designpattern.bridge;

/**
 * @author miclefengzss
 */
public class Vivo implements PhoneBrand {

    @Override
    public void open() {
        System.out.println(" vivo open.");
    }

    @Override
    public void call() {
        System.out.println(" vivo call.");
    }

    @Override
    public void close() {
        System.out.println(" vivo close.");
    }
}
