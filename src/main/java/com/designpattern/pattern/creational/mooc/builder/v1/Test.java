package com.designpattern.pattern.creational.mooc.builder.v1;

/**
 * @author miclefengzss
 * 2022/1/10 下午5:16
 */
public class Test {

    public static void main(String[] args) {
        CourseBuilder courseBuilder = new CourseActualBuilder();
        Designer designer = new Designer();
        designer.setCourseBuilder(courseBuilder);
        final Course course = designer.makeCourse("name", "ppt", "video", "article", "qa");
        System.out.println(course);
    }
}
