package com.lw.concurrency.designpattern.simple_factory;

public class ScalaCourse implements ICourse{
    @Override
    public void record() {
        System.out.println("start Scala......");
    }
}
