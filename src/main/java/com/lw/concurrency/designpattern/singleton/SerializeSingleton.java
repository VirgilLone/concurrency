package com.lw.concurrency.designpattern.singleton;

import java.io.Serializable;

/**
 * readResolve() 避免序列化中生成不同对象，源码在io的readObject中
 */
public class SerializeSingleton implements Serializable {

    private SerializeSingleton(){}

    private static final SerializeSingleton INSTANCE = new SerializeSingleton();

    public static SerializeSingleton getInstance(){
        return INSTANCE;
    }

    // readResolve方法避免序列化之后生产不同的实例
    public Object readResolve(){
        return INSTANCE;
    }

}
