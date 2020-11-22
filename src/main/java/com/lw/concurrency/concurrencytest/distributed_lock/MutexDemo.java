package com.lw.concurrency.concurrencytest.distributed_lock;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.retry.ExponentialBackoffRetry;

import java.util.concurrent.TimeUnit;

/**
 * zk的分布式锁的实现
 */
public class MutexDemo {

    public static void main(String[] args) {
        CuratorFramework curatorFramework = CuratorFrameworkFactory.builder()
                .connectString("127.0.0.1:2181")
                .sessionTimeoutMs(5000)
                .retryPolicy(new ExponentialBackoffRetry(1000, 3))
                .connectionTimeoutMs(4000)
                .build();
        curatorFramework.start(); //启动curator

        InterProcessMutex lock = new InterProcessMutex(curatorFramework, "/locks");

        /*for (int i=0;i<5;i++){
            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+"==>尝试抢占锁。。。");
                try {
                    lock.acquire(); //抢占锁，没有抢到则阻塞
                    System.out.println(Thread.currentThread().getName()+" 获取锁成功！");
                    Thread.sleep(4000);
                    lock.release();

                    System.out.println(Thread.currentThread().getName()+" 释放锁成功！");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            },"t-"+i).start();
        }*/
        try {
            if(lock.acquire(2000, TimeUnit.MILLISECONDS)){
                System.out.println(Thread.currentThread().getName()+"==> 机器1获取锁成功！");
                System.out.println("机器1开始执行任务。。。");
                Thread.sleep(10000);
                lock.release();
            }else{
                System.out.println("机器1未获取到分布式锁，跳过执行！");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }




    }



}
