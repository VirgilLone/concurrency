package com.lw.factorytest;

/**
 * @description: 默认工厂
 * @author: luo.wen
 * @createTime: 2019/5/8
 */
public class DefaultFactory extends AbstractFactory{

    private BMWFactory defaultFactory = new BMWFactory();//默认宝马工厂

    @Override
    protected Car getCar1() {
        return defaultFactory.getCar1();
    }
}
