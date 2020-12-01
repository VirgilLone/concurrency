package com.lw.concurrency.designpattern.singleton;

/**
 * 枚举实现虽然优雅且不存在线程安全问题，也不会反射破坏，
 * 但是在声明一个枚举的时候就会把枚举当成一个常量放在一个Map里面，
 * 不适合大批量使用单例对象，会造成内存浪费
 */
public enum  EnumSingleton {

    INSTANCE;

    private Object data;

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public static EnumSingleton getInstance(){
        return INSTANCE;
    }

    public static void main(String[] args) {
        EnumSingleton instance = EnumSingleton.getInstance();
        instance.setData(new Object());
        EnumSingleton instance2 = EnumSingleton.getInstance();

    }
}
