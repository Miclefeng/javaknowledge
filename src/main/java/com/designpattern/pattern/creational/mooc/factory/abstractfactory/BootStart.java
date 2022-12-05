package com.designpattern.pattern.creational.mooc.factory.abstractfactory;

/**
 * @author miclefengzss
 * 2021/12/2 上午10:31
 */
public class BootStart {

    public static void main(String[] args) {
        final JavaCourseFactory javaCourseFactory = new JavaCourseFactory();
        final Article javaArticle = javaCourseFactory.getArticle();
        final Video javaVideo = javaCourseFactory.getVideo();
        javaVideo.produce();
        javaArticle.produce();
    }
}
