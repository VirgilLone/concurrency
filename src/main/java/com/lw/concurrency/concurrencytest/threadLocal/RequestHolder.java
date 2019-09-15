package com.lw.concurrency.concurrencytest.threadLocal;

/**
 * ThreadLocal线程封闭，存储绑定到线程上面的信息
 */
public class RequestHolder {

    private final static ThreadLocal<Long> requestHolder = new ThreadLocal<>();

    public static void add(Long id) {
        requestHolder.set(id);
    }

    public static Long getId() {
        return requestHolder.get();
    }

    public static void remove() {
        requestHolder.remove();
    }
}
