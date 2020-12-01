package com.lw.concurrency.designpattern.abstract_factory;

/**
 * 抽象工厂 整合产品族和产品等级
 */
public abstract class CourseFactory {

    void init(){
        System.out.println("初始化基础数据！");
    }

    protected abstract INote createNote();

    protected abstract IVideo createVideo();

}
