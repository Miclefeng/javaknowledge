package com.designpattern.pattern.creational.factory.abstractfactory;

/**
 * @author miclefengzss
 * 2021/12/2 上午11:02
 */
public class FeArticle extends Article {
    @Override
    public void produce() {
        System.out.println("FeArticle Produce.");
    }
}
