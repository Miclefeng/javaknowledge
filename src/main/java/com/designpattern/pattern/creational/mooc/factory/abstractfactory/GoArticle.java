package com.designpattern.pattern.creational.mooc.factory.abstractfactory;

/**
 * @author miclefengzss
 * 2021/12/2 上午11:02
 */
public class GoArticle extends Article {
    @Override
    public void produce() {
        System.out.println("GoArticle Produce.");
    }
}
