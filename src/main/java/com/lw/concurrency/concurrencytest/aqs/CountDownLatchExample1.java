package com.lw.concurrency.concurrencytest.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Slf4j
public class CountDownLatchExample1 {

    private final static int threadCount = 3;

    public static void main(String[] args) throws Exception {

        ExecutorService exec = Executors.newCachedThreadPool();

        final CountDownLatch countDownLatch = new CountDownLatch(threadCount);

        for (int i = 0; i < threadCount; i++) {
            final int threadNum = i;
            exec.execute(() -> {
                try {
                    test(threadNum);
                } catch (Exception e) {
                    log.error("arise exception...", e);
                } finally {
                    countDownLatch.countDown();
                }
            });
        }
        //等待CountDownLatch里的计数器减到0才执行后面的语句
        countDownLatch.await();
        //countDownLatch.await(10, TimeUnit.MICROSECONDS);
        log.info("finish...");
        exec.shutdown();
    }

    private static void test(int threadNum) throws Exception {
        /*Thread.sleep(100);
        log.info("{}", threadNum);
        Thread.sleep(100);*/
        if(threadNum==0){
            Thread.sleep(100);
            log.info("{}", "处理子任务1.。。");
            Thread.sleep(100);
        }else if(threadNum==1){
            Thread.sleep(100);
            log.info("{}", "处理子任务2.。。");
            Thread.sleep(100);
        }else if(threadNum==2){
            Thread.sleep(5000);
            log.info("{}", "处理子任务3.。。");
            Thread.sleep(5000);
        }
    }
}
