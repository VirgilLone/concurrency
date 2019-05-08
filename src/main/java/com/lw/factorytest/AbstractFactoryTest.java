package com.lw.factorytest;

/**
 * @description: 测试类
 * @author: luo.wen
 * @createTime: 2019/5/8
 */
public class AbstractFactoryTest {

    public static void main(String[] args) {
        DefaultFactory defaultFactory=new DefaultFactory();
        System.out.println(defaultFactory.getCar("Benz").getName());

    }

}
