package com.lw.concurrency.designpattern.factory_method;

public class ScalaCourse implements ICourse {
    @Override
    public void record() {
        System.out.println("start Scala......");
    }
}
