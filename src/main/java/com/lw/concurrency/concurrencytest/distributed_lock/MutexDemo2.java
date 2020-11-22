package com.lw.concurrency.concurrencytest.distributed_lock;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.retry.ExponentialBackoffRetry;

import java.util.concurrent.TimeUnit;

public class MutexDemo2 {

    public static void main(String[] args) {
        CuratorFramework curatorFramework = CuratorFrameworkFactory.builder()
                .connectString("127.0.0.1:2181")
                .sessionTimeoutMs(5000)
                .retryPolicy(new ExponentialBackoffRetry(1000, 3))
                .connectionTimeoutMs(4000)
                .build();
        curatorFramework.start(); //启动curator

        InterProcessMutex lock = new InterProcessMutex(curatorFramework, "/locks");


        try {
            if(lock.acquire(2000, TimeUnit.MILLISECONDS)){
                System.out.println(Thread.currentThread().getName()+"==> 机器2获取锁成功！");
                System.out.println("机器2开始执行任务。。。");
                Thread.sleep(10000);
                lock.release();
            }else{
                System.out.println("机器2未获取到分布式锁，跳过执行！");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }




    }

}
