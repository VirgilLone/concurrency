package com.lw.concurrency.designpattern.simple_factory;

public class Test {

    public static void main(String[] args) {
//        ICourse course = new JavaCourse();
//        course.record();

//        ICourse course = new CourseFactory().create("java");
//        course.record();

//        ICourse course = new CourseFactory().create("com.example.demo.test.designpattern.simple_factory.ScalaCourse");
//        course.record();

        ICourse course = new CourseFactory().create(ScalaCourse.class);
        course.record();

    }
}
