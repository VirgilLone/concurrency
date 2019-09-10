package com.lw.thread;

import java.util.concurrent.CountDownLatch;

/**
 * @description: java.util.concurrent.CountDownLatch代替MyCountDown
 * @author: luo.wen
 * @createTime: 2019/9/10
 */
public class ImportThread_advanced extends Thread {
    private CountDownLatch threadsSignal;

    public ImportThread_advanced(CountDownLatch threadsSignal) {
        this.threadsSignal = threadsSignal;
    }
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + "开始...");
        //todo biz

        threadsSignal.countDown();//线程结束时计数器减1
        System.out.println(Thread.currentThread().getName() + "结束. 还有 " + threadsSignal.getCount() + " 个线程");
    }

}
