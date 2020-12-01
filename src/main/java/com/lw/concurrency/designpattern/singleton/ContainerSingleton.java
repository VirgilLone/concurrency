package com.lw.concurrency.designpattern.singleton;

import com.lw.concurrency.annotation.NotRecommend;
import com.lw.concurrency.annotation.NotThreadSafe;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 容器式单例
 * 还是无法解决线程安全问题
 */
@NotThreadSafe
@NotRecommend
public class ContainerSingleton {

    private ContainerSingleton(){}

    private static Map<String,Object> ioc = new ConcurrentHashMap<>();

    public static Object getInstance(String className){
        Object instance = null;
        if(!ioc.containsKey(className)){
            try {
                instance = Class.forName(className).newInstance();
                ioc.put(className, instance);
            }catch (Exception e ){
                e.printStackTrace();
            }
            return instance;
        }else return ioc.get(className);


    }



}
