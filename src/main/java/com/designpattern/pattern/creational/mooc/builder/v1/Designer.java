package com.designpattern.pattern.creational.mooc.builder.v1;

/**
 * @author miclefengzss
 * 2022/1/10 下午5:15
 */
public class Designer {

    private CourseBuilder courseBuilder;

    public Course makeCourse(String courseName, String coursePPT, String courseVideo, String courseArticle, String courseQA) {
        this.courseBuilder.buildCourseName(courseName);
        this.courseBuilder.buildCoursePPT(coursePPT);
        this.courseBuilder.buildCourseVideo(courseVideo);
        this.courseBuilder.buildCourseArticle(courseArticle);
        this.courseBuilder.buildCourseQA(courseQA);
        return this.courseBuilder.makeCourse();
    }

    public void setCourseBuilder(CourseBuilder courseBuilder) {
        this.courseBuilder = courseBuilder;
    }
}
