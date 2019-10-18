package com.lw.concurrency.concurrencytest.future_enhance;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @description: 基于CompletableFuture创建任务和链式处理多个任务，并实现按照任务完成的先后顺序获取任务的结果
 * @author: luo.wen
 * @createTime: 2019/10/17
 */
public class TestCompletableFuture {
    private static final String commandstr01 = "hahah";
    private static final String commandstr02 = "hahah";
    private static final String commandstr03 = "hahah";
    private static final String commandstr04 = "hahah";

    public static void main(String[] args) throws InterruptedException, ExecutionException {

        ExecutorService executorService = Executors.newCachedThreadPool();

        CompletableFuture.supplyAsync(new MyThreadt444(commandstr02),executorService).whenComplete((result, e) -> {
            //执行线程执行完以后的操作。
            System.out.println(result + " " + e);
        }).exceptionally((e) -> {
            //抛出异常
            System.out.println("exception " + e);
            return "exception";
        });

        CompletableFuture.supplyAsync(new MyThreadt333(commandstr02),executorService).whenComplete((result, e) -> {
            //执行线程执行完以后的操作。
            System.out.println(result + " " + e);
        }).exceptionally((e) -> {
            System.out.println("exception " + e);
            return "exception";
        });
    }


}
