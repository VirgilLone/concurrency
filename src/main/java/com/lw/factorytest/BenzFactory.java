package com.lw.factorytest;

/**
 * @description:
 * @author: luo.wen
 * @createTime: 2019/5/8
 */
public class BenzFactory extends AbstractFactory{
    @Override
    protected Car getCar1() {
        return new Benz();
    }
}
