package com.designpattern.priciple.openclose;

/**
 * @author miclefengzss
 * @create 2021-03-15 下午11:07
 */

public class Rectangle extends Shape {

    public Rectangle() {
        super.m_type = 1;
    }

    @Override
    public void draw() {
        System.out.println("Rectangle");
    }
}
