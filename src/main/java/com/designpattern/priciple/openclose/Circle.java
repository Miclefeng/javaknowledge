package com.designpattern.priciple.openclose;

/**
 * @author miclefengzss
 * @create 2021-03-15 下午11:08
 */

public class Circle extends Shape {

    public Circle() {
        super.m_type = 2;
    }

    @Override
    public void draw() {
        System.out.println("Circle");
    }
}
