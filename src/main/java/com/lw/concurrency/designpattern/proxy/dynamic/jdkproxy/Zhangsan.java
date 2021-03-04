package com.lw.concurrency.designpattern.proxy.dynamic.jdkproxy;

public class Zhangsan implements IPerson {

    @Override
    public void findLove() {
        System.out.println("张三要求：貌美大长腿");
    }

    @Override
    public void buyInsure() {
        System.out.println("10万");
    }
}
