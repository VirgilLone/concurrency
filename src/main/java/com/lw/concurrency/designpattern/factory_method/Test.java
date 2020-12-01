package com.lw.concurrency.designpattern.factory_method;


/**
 * 简单工厂是产品的工厂，而工厂方法是工厂的工厂
 */
public class Test {
    public static void main(String[] args) {
        ICourseFactory factory = new JavaCourseFactory();
        ICourse iCourse = factory.create();
        iCourse.record();
    }
}
