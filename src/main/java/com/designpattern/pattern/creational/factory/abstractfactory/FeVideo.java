package com.designpattern.pattern.creational.factory.abstractfactory;

/**
 * @author miclefengzss
 * 2021/12/2 上午10:33
 */
public class FeVideo implements Video {
    
    @Override
    public void produce() {
        System.out.println("FeVideo Produce.");
    }
}
