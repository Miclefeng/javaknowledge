package com.designpattern.pattern.creational.factory.factorymethod;

/**
 * @author miclefengzss
 * 2021/12/2 上午10:33
 */
public class GoVideo implements Video {
    
    @Override
    public void produce() {
        System.out.println("GoVideo Produce.");
    }
}
