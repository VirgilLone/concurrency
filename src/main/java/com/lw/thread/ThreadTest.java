package com.lw.thread;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @description: 等待某线程执行完毕再执行其余线程的实现
 * @author: luo.wen
 * @createTime: 2019/9/10
 */
public class ThreadTest {

    public static void main(String[] args) throws Exception {
//        test1();
//        test2();
//        test3();
        test_callable();
    }

    /**
     * 带返回值的线程
     * @throws ExecutionException
     * @throws InterruptedException
     */
    private static void test_callable() throws ExecutionException, InterruptedException {
        System.out.println("----程序开始运行----");
        Date date1 = new Date();

        int taskSize = 5;
        // 创建一个固定数目线程的线程池
        ExecutorService pool = Executors.newFixedThreadPool(taskSize);
        // 创建多个有返回值的任务
        List<Future> list = new ArrayList<Future>();
        for (int i = 0; i < taskSize; i++) {
            Callable c = new ImportThread_callable(i + " ");
            // 执行任务并获取Future对象
            Future f = pool.submit(c);
            // System.out.println(">>>" + f.get().toString());
            list.add(f);
        }
        // 关闭线程池
        pool.shutdown();

        // 获取所有并发任务的运行结果
        for (Future f : list) {
            // 从Future对象上获取任务的返回值，并输出到控制台
            System.out.println("---->" + f.get().toString());
        }

        Date date2 = new Date();
        System.out.println("----程序结束运行----，程序运行时间【"
                + (date2.getTime() - date1.getTime()) + "毫秒】");
    }

    private static void test3() throws InterruptedException {
        long tStart = System.currentTimeMillis();
        System.out.println(Thread.currentThread().getName() + "开始-->");
        CountDownLatch threadSignal = new CountDownLatch(6);//初始化countDown
        for (int ii = 0; ii < 6; ii++) {//开threadNum个线程
//            final Iterator<String> itt = it.get(ii);
            Thread t = new ImportThread_advanced(ii, threadSignal);
            t.start();
        }
        threadSignal.await();//等待所有子线程执行完
        System.out.println(Thread.currentThread().getName() + "<--结束.");
        long tEnd = System.currentTimeMillis();
        System.out.println("总共用时:" + (tEnd - tStart) + "millions");
    }

    private static void test2() throws InterruptedException {
        long tStart = System.currentTimeMillis();
        System.out.println(Thread.currentThread().getName() + "开始-->");
        MyCountDown c = new MyCountDown(6);//初始化countDown
        for (int ii = 0; ii < 6; ii++) {//开6个子线程
            Thread t = new ImportThread(c);
            t.start();
        }
        while (true) {//等待所有子线程执行完
            if (!c.hasNext()) {
                break;
            }
            Thread.sleep(500);
        }
        System.out.println(Thread.currentThread().getName() + "<--结束.");
        long tEnd = System.currentTimeMillis();
        System.out.println("总共用时:" + (tEnd - tStart) + "millions");

    }

    private static void test1() {
        long tStart = System.currentTimeMillis();
        System.out.println(Thread.currentThread().getName() + "开始-->");
        for (int ii = 0; ii < 6; ii++) {//开6个线程
            Runnable r = new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName() + "开始");
                    //todo something
                    System.out.println(Thread.currentThread().getName() + "结束了.");
                }
            };
            Thread t = new Thread(r);
            t.start();
        }
        System.out.println(Thread.currentThread().getName() + "<--结束.");
        long tEnd = System.currentTimeMillis();
        System.out.println("总共用时:" + (tEnd - tStart) + "millions");
    }

}
