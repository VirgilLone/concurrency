package com.lw.concurrency.designpattern.abstract_factory;

public class ScalaVideo implements IVideo{
    @Override
    public void recording() {
        System.out.println("录像。。。");
    }
}
