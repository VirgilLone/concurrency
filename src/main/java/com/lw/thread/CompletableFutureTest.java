package com.lw.thread;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @description:
 * @author: luo.wen
 * @createTime: 2019/11/1
 */
@Slf4j
public class CompletableFutureTest {

    public  static void asyncCallback4() throws ExecutionException, InterruptedException {
        CompletableFuture<String> ref1=  CompletableFuture.supplyAsync(()->{
            try {
                log.info(Thread.currentThread().getName()+"：开始执行任务1。。。");
//                TimeUnit.SECONDS.sleep(1);
            } catch (Exception e) {
                e.printStackTrace();
            }
            log.info(Thread.currentThread().getName()+"：任务1完成");
            return null;
        });

        CompletableFuture<String> ref2= CompletableFuture.supplyAsync(()->{
            try {
//                String remoteRes = HttpUtils.get("");
                log.info(Thread.currentThread().getName()+"：开始执行任务2。。。");
                Thread.sleep(10000);
                log.info(Thread.currentThread().getName()+"：任务2完成");
                return "OK";
            } catch (Exception e) {
                e.printStackTrace();
            }
            return "ERROR";
        });
        CompletableFuture<String> ref2_1=ref2.thenApplyAsync(value->{
            log.info(Thread.currentThread().getName()+"：运行任务2的子任务。。。");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return  null;
        });

        CompletableFuture<String> ref3= CompletableFuture.supplyAsync(()->{
            try {
                log.info(Thread.currentThread().getName()+"：开始执行任务3。。。");
                Thread.sleep(5000);
            } catch (Exception e) {
                e.printStackTrace();
            }
            log.info(Thread.currentThread().getName()+"：任务3完成");
            return null;
        });

        Thread.sleep(10000);
        log.info("{}---->{}",Thread.currentThread().getName(),ref2_1.get());
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        asyncCallback4();
    }
}
