package com.lw.factorytest;

/**
 * @description: 抽象工厂类
 * @author: luo.wen
 * @createTime: 2019/5/8
 */
public abstract class AbstractFactory {

    protected abstract Car getCar1();

    public Car getCar(String name){
        if("BMW".equalsIgnoreCase(name)){
            return new BMWFactory().getCar1();
        }else if("Benz".equalsIgnoreCase(name)){
            return new BenzFactory().getCar1();
        }else {
            System.out.println("无对应工厂，无法生产");
            return null;
        }

    }
}
