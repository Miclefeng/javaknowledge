package com.designpattern.pattern.creational.factory.abstractfactory;

/**
 * @author miclefengzss
 * 2021/12/2 上午10:34
 */
public class JavaCourseFactory implements CourseFactory {

    @Override
    public Video getVideo() {
        return new JavaVideo();
    }

    @Override
    public Article getArticle() {
        return new JavaArticle();
    }
}
