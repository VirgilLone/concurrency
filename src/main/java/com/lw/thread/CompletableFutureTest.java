package com.lw.thread;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @description:
 * @author: luo.wen
 * @createTime: 2019/11/1
 */
@Slf4j
public class CompletableFutureTest {
    static ExecutorService pool = Executors.newCachedThreadPool();

    public  static void asyncCallback4() throws ExecutionException, InterruptedException {
        CompletableFuture<Void> ref1=  CompletableFuture.runAsync(()->{
            try {
                log.info(Thread.currentThread().getName()+"：开始执行任务1。。。");
//                TimeUnit.SECONDS.sleep(1);
            } catch (Exception e) {
                e.printStackTrace();
            }
            log.info(Thread.currentThread().getName()+"：任务1完成");
        },pool);

        CompletableFuture<String> ref2= CompletableFuture.supplyAsync(()->{
            try {
                log.info(Thread.currentThread().getName()+"：开始执行任务2。。。");
//                String remoteRes = HttpUtils.get("http://localhost:8080/api/find_all_yinyue");
                Thread.sleep(5000);
                log.info(Thread.currentThread().getName()+"：任务2完成");
                return "SUCCESS";
            } catch (Exception e) {
                e.printStackTrace();
            }
            return "ERROR";
        },pool).thenApplyAsync(value->{
            if(value!="SUCCESS"){
                return "rpc error";
            }
            log.info(Thread.currentThread().getName()+"：运行任务2的子任务。。。");
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.info(Thread.currentThread().getName()+"：任务2的子任务也完成");
            return  value;
        },pool).thenApplyAsync((v2)-> v2+"666",pool);
        log.info("插入主线程继续毫无相关的任务1111。。。。。。。");
        /*CompletableFuture<String> ref2_1=ref2.thenApplyAsync(value->{
            log.info(Thread.currentThread().getName()+"：运行任务2的子任务。。。");
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return  value;
        });*/

        CompletableFuture<Void> ref3= CompletableFuture.supplyAsync(()->{
            try {
                log.info(Thread.currentThread().getName()+"：开始执行任务3。。。");
                Thread.sleep(5000);
            } catch (Exception e) {
                e.printStackTrace();
            }
            log.info(Thread.currentThread().getName()+"：任务3完成");
            return null;
        },pool).thenRunAsync(() -> System.out.println("hello world"),pool);   //对上一步的计算结果不关心，执行下一个操作

//        Thread.sleep(20000);
//        log.info("{}---->{}",Thread.currentThread().getName(),ref2.get());  //get()和join()都会阻塞当前线程
        log.info("插入主线程继续毫无相关的任务2222。。。。。。。");
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        asyncCallback4();
    }
}
