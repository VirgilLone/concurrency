package com.lw.test;


import com.lw.factorytest.BMW;

/**
 * 泛型模版
 * 1。在声明类时，在类后面指定。在声明和创建它时，模版中的数据类型应该保持一致
 * 2。在声明方法时，在返回值前面指定。
 * @param <T>
 */
class NBList<T>{

    public void add(T e){
    }

    public <A> void m1(A a){
        System.out.println(a.getClass());
    }

}

public class FanXing {
    public static void main(String[] args) {
        NBList<String> stringNBList = new NBList<>();

//        NBList<Parent> childNBList = new NBList<Child>();
//        NBList<Child> childNBList = new NBList<Parent>();

        new NBList<Parent>().<BMW>m1(new BMW());

    }

}

class Parent{}
class Child extends Parent{}
class Sub extends Child{}
