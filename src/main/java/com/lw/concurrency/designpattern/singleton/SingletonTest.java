package com.lw.concurrency.designpattern.singleton;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SingletonTest implements Runnable{

    @Override
    public void run() {
        SingletonExample4 instance = SingletonExample4.getInstance();
        System.out.println(Thread.currentThread().getName()+" : "+instance);
    }

    public static void main(String[] args) throws Exception {
        Thread t1 = new Thread(new SingletonTest());
        Thread t2 = new Thread(new SingletonTest());
        t1.start();
        t2.start();

        System.out.println("~");


//        Class<?> clazz = SingletonExample5.class;
//        Constructor<?> c = clazz.getDeclaredConstructor(null);
//
//        c.setAccessible(true);
//
//        Object o = c.newInstance();
//        System.out.println(o);
//        Object o2 = c.newInstance();
//        System.out.println(o2);

    }
}
