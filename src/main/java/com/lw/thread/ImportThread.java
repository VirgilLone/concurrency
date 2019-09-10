package com.lw.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * @description:
 * @author: luo.wen
 * @createTime: 2019/9/10
 */
public class ImportThread extends Thread {

    private MyCountDown c;

    public ImportThread(MyCountDown c) {
        this.c = c;
    }

    private CountDownLatch threadSignal;
    public ImportThread(CountDownLatch threadSignal) {
        this.threadSignal = threadSignal;
    }

    private static List<Thread> runningThreads = new ArrayList<Thread>();

    public ImportThread() {
    }

    @Override
    public void run() {
//        regist(this);//线程开始时注册
        System.out.println(Thread.currentThread().getName() + "开始...");//打印开始标记
        //todo something
//        unRegist(this);//线程结束时取消注册
        c.countDown();//计时器减1
        System.out.println(Thread.currentThread().getName() + "结束.");//打印结束标记
    }

    public void regist(Thread t) {
        synchronized (runningThreads) {
            runningThreads.add(t);
        }
    }

    public void unRegist(Thread t) {
        synchronized (runningThreads) {
            runningThreads.remove(t);
        }
    }

    public static boolean hasThreadRunning() {
        //通过判断runningThreads是否为空就能知道是否还有线程未执行完
        return (runningThreads.size() > 0);
    }
}
