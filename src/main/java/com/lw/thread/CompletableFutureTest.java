package com.lw.thread;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @description:
 * @author: luo.wen
 * @createTime: 2019/11/1
 */
@Slf4j
public class CompletableFutureTest {

    /** ref1、ref2、ref3同时执行，ref2的子任务在ref2完成后执行
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public  static void asyncCallback4() throws ExecutionException, InterruptedException {
        ExecutorService pool = Executors.newCachedThreadPool();
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
                String remoteRes = HttpUtils.get("http://localhost:8080/api/find_all_yinyue");
                Thread.sleep(5000);
                log.info(Thread.currentThread().getName()+"：任务2完成");
                return "SUCCESS";
            } catch (Exception e) {
                e.printStackTrace();
                return e;
            }
//            return "ERROR";
        },pool).thenApplyAsync(value->{
            if(!"SUCCESS".equals(value)){
                log.error("任务2 出现异常！");
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
//        asyncCallback4();
        asyncCallback();
    }

    /**
     * 两个任务并发任务都完成后，取结果继续执行
     */
    public  static void asyncCallback()  {
        ExecutorService pool = Executors.newCachedThreadPool();
        CompletableFuture<Void> a=  CompletableFuture.runAsync(()->{
            try {
                log.info("开始执行任务A。。。");
                TimeUnit.SECONDS.sleep(10);
//                Thread.sleep(8000);
            } catch (Exception e) {
                e.printStackTrace();
            }
            log.info("：任务A完成");
        },pool);

        CompletableFuture<Void> b= CompletableFuture.supplyAsync(()->{
            try {
                log.info("开始执行任务B。。。");
                Thread.sleep(8000);
            } catch (Exception e) {
                e.printStackTrace();
            }
            log.info("任务B完成");
            return null;
        },pool);

        // a+b同时执行完毕之后 --> 取两个任务的结果，main线程继续往后执行
        // 后续的处理不需要返回值: a.thenAcceptBoth(b, (resultA, resultB) -> {});
        CompletableFuture<String> stringCompletableFuture = a.thenCombine(b, (resultA, resultB) -> "result A + B").thenApplyAsync(r->{
            System.out.println(r);
            if(!StringUtils.isEmpty(r)){
                log.info("succccccc");
                return "success";
            }
            log.error("occur error");
            return "error";
        },pool);

        log.info("other...");

        try {
            log.info("拿到最终返回值--》"+stringCompletableFuture.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        log.info("test end...");

        pool.shutdown();
    }


}
