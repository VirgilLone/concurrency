package com.lw.concurrency.designpattern.proxy.static_;


public class Test {
    public static void main(String[] args) {
        ZhangLaosan zhangLaosan = new ZhangLaosan(new ZhangSan());
        zhangLaosan.findLove();
    }
}
