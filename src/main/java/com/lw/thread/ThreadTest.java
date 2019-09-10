package com.lw.thread;

import java.util.concurrent.CountDownLatch;

/**
 * @description:
 * @author: luo.wen
 * @createTime: 2019/9/10
 */
public class ThreadTest {

    public static void main(String[] args) throws InterruptedException {
//        test1();
//        test2();
        test3();
    }

    private static void test3() throws InterruptedException {
        long tStart = System.currentTimeMillis();
        System.out.println(Thread.currentThread().getName() + "开始-->");
        CountDownLatch threadSignal = new CountDownLatch(6);//初始化countDown
        for (int ii = 0; ii < 6; ii++) {//开threadNum个线程
//            final Iterator<String> itt = it.get(ii);
            Thread t = new ImportThread_advanced( threadSignal);
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
