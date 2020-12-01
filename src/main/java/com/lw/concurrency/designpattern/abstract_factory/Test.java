package com.lw.concurrency.designpattern.abstract_factory;


public class Test {
    public static void main(String[] args) {
        CourseFactory courseFactory = new ScalaCourseFactory();
        courseFactory.createNote().edit();
        courseFactory.createVideo().recording();

    }
}
