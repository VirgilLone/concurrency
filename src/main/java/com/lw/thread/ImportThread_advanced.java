package com.lw.thread;

import java.util.concurrent.CountDownLatch;

/**
 * @description: java.util.concurrent.CountDownLatch代替MyCountDown
 * @author: luo.wen
 * @createTime: 2019/9/10
 */
public class ImportThread_advanced extends Thread {
    private CountDownLatch threadsSignal;

    private int i;

    public ImportThread_advanced(CountDownLatch threadsSignal) {
        this.threadsSignal = threadsSignal;
    }
    public ImportThread_advanced(int i,CountDownLatch threadsSignal) {
        this.i = i;
        this.threadsSignal = threadsSignal;
    }
    @Override
    public void run() {
        shijieFun(i);
    }

    private void shijieFun(int i){
        System.out.println(Thread.currentThread().getName() + "开始"+"["+i+"]"+"...");
        //todo biz

        threadsSignal.countDown();//线程结束时计数器减1
        System.out.println(Thread.currentThread().getName() + "结束. 还有 " + threadsSignal.getCount() + " 个线程");
    }

}
