package com.designpattern.priciple.openclose;

/**
 * @author miclefengzss
 * @create 2021-03-15 下午11:07
 */

public class Triangle extends Shape{

    public Triangle() {
        super.m_type = 3;
    }

    @Override
    public void draw() {
        System.out.println("Triangle");
    }
}
