package com.lw.concurrency.designpattern.simple_factory;

public class CourseFactory {

    public ICourse create(Class<? extends ICourse> clazz) {
//        if ("java".equals(name))
//             return new JavaCourse();
//        if ("scala".equals(name))
//            return new ScalaCourse();
//        else
//            return null;


//        try {
//            if (null != className || !"".equals(className)) {
//                return (ICourse) Class.forName(className).newInstance();
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        return null;


        try {
            if (clazz != null) {
                return clazz.newInstance();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }
}


