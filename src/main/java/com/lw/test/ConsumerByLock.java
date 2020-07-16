package com.lw.test;

import java.util.Queue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class ConsumerByLock implements Runnable{

    private Queue<String> msg;
    private int maxSize;
    private Lock lock;
    private Condition condition;

    public ConsumerByLock(Queue<String> msg, int maxSize) {
        this.msg = msg;
        this.maxSize = maxSize;
    }
    public ConsumerByLock(Queue<String> msg, int maxSize, Lock lock, Condition condition) {
        this.msg = msg;
        this.maxSize = maxSize;
        this.lock = lock;
        this.condition = condition;
    }

    @Override
    public void run() {
        while (true){
//            synchronized (msg){
            lock.lock();
                while (msg.isEmpty()){
                    try {
//                        msg.wait();
                        condition.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println("消费者消费："+msg.remove());
//                msg.notify();
                condition.signal();
//            }
            lock.unlock();

        }

    }
}
