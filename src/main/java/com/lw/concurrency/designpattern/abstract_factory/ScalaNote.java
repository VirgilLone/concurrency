package com.lw.concurrency.designpattern.abstract_factory;

public class ScalaNote implements INote {
    @Override
    public void edit() {
        System.out.println("写笔记。。。");
    }
}
