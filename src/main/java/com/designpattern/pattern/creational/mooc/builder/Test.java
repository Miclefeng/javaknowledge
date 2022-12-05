package com.designpattern.pattern.creational.mooc.builder;

/**
 * @author miclefengzss
 * 2022/1/10 下午5:25
 */
public class Test {

    public static void main(String[] args) {
        final Course course = new Course.CourseBuilder().buildCourseName("name").buildCoursePPT("ppt").buildCourseArticle("article").buildCourseVideo("video").buildCourseQA("QA").build();
        System.out.println(course);
    }
}
