package com.designpattern.pattern.creational.mooc.factory.abstractfactory;

/**
 * @author miclefengzss
 * 2021/12/2 上午10:34
 */
public class FeCourseFactory implements CourseFactory {

    @Override
    public Video getVideo() {
        return new FeVideo();
    }

    @Override
    public Article getArticle() {
        return new FeArticle();
    }
}
