package com.lw.concurrency.designpattern.abstract_factory;

public class ScalaCourse implements ICourse {
    @Override
    public void record() {
        System.out.println("start Scala......");
    }
}
