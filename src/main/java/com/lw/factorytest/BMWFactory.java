package com.lw.factorytest;

/**
 * @description: 宝马工厂
 * @author: luo.wen
 * @createTime: 2019/5/8
 */
public class BMWFactory extends AbstractFactory{
    @Override
    protected Car getCar1() {
        return new BMW();
    }
}
