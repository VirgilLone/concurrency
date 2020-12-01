package com.lw.concurrency.designpattern.singleton;

/**
 * 静态内部类实现单例，实现优雅
 * 缺点：以上1-7都能被反射破坏，可在构造器里抛出异常解决
 */
public class SingletonWithInnerClass {

    private SingletonWithInnerClass(){
        throw new RuntimeException("不允许执行构造器！");
    }

    public static SingletonWithInnerClass getInstance(){
        return LazyHolder.INSTANCE;
    }

    private static class LazyHolder{
        private static final SingletonWithInnerClass INSTANCE = new SingletonWithInnerClass();
    }


}
