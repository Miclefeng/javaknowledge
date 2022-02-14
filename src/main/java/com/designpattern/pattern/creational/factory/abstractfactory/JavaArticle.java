package com.designpattern.pattern.creational.factory.abstractfactory;

/**
 * @author miclefengzss
 * 2021/12/2 上午11:02
 */
public class JavaArticle extends Article {
    @Override
    public void produce() {
        System.out.println("JavaArticle Produce.");
    }
}
