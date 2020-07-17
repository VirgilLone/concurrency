package com.lw.test;

import java.util.Queue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class ProducerByLock implements Runnable{

    private Queue<String> msg;
    private int maxSize;
    private Lock lock;
    private Condition condition;

    public ProducerByLock(Queue<String> msg, int maxSize) {
        this.msg = msg;
        this.maxSize = maxSize;
    }

    public ProducerByLock(Queue<String> msg, int maxSize, Lock lock, Condition condition) {
        this.msg = msg;
        this.maxSize = maxSize;
        this.lock = lock;
        this.condition = condition;
    }

    @Override
    public void run() {
        int i=0;

        while (true){
            i++;
//            synchronized (msg){
            lock.lock();
                while (msg.size()==maxSize){
                    // 生产满了，阻塞当前生产者线程
                    try {
//                        msg.wait(); // wait一定会释放锁
                        condition.await(); // 阻塞线程并释放锁 state--->0 加入到condition条件队列
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                msg.add("消息"+i);
                System.out.println("生产者"+ Thread.currentThread().getName()+"生产消息"+i);
//                msg.notify();
                condition.signal(); //条件队列的尾节点加入到aqs的同步队列，唤醒并加入到锁竞争acquireQueued，得到锁才能从await方法返回
//            }
            lock.unlock();
        }


    }
}
