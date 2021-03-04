package com.lw.concurrency.designpattern.proxy.dynamic.jdkproxy;

public class ZhaoLiu implements IPerson {
    @Override
    public void findLove() {
        System.out.println("赵六要求：肤白貌美大长腿");
    }

    @Override
    public void buyInsure() {
        System.out.println("20万");

    }
}
