package com.lw.concurrency.concurrencytest.aqs;

import java.util.Timer;
import java.util.WeakHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @description:
 * @author: luo.wen
 * @createTime: 2019/9/19
 */
public class ScheduledExecutorTest implements Runnable{

    private String jobName ;

    public ScheduledExecutorTest(String jobName) {
        super();
        this.jobName = jobName;
    }
    @Override
    public void run() {
        System.out.println("execute---> " + jobName);
    }

    public static void main(String[] args) {
        ScheduledExecutorService service = Executors.newScheduledThreadPool(4);

        long initialDelay1 = 2;
        long period1 = 3;
        // 从现在开始3秒钟之后，每隔5秒钟执行一次job1。每次执行时间为上一次任务开始起向后推一个时间间隔
        /*service.scheduleAtFixedRate(
                new ScheduledExecutorTest("job1"), initialDelay1,period1,TimeUnit.SECONDS)
                ;*/
        service.scheduleAtFixedRate(()->{
            System.out.println(System.currentTimeMillis()+"<-----"+Thread.currentThread().getName());
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, initialDelay1,period1, TimeUnit.SECONDS);

        //service.shutdown();

        /*long initialDelay2 = 1;
        long delay2 = 1;
        // 每次执行时间为上一次任务结束起向后推一个时间间隔
        service.scheduleWithFixedDelay(
                new ScheduledExecutorTest("job2"), initialDelay2,
                delay2, TimeUnit.SECONDS);*/
    }
}
