package com.lw.test;

import java.util.Queue;
import java.util.concurrent.TimeUnit;

public class Producer implements Runnable{

    private Queue<String> msg;
    private int maxSize;

    public Producer(Queue<String> msg, int maxSize) {
        this.msg = msg;
        this.maxSize = maxSize;
    }

    @Override
    public void run() {
        int i=0;

        while (true){
            i++;
            synchronized (msg){
                while (msg.size()==maxSize){
                    // 生产满了，阻塞当前生产者线程
                    try {
                        msg.wait(); // wait一定会释放锁
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
                System.out.println("生产者生产消息"+i);
                // 唤醒处于阻塞状态下的线程，这里就是消费者线程
                // （从等待队列中唤醒线程然后迁移到同步队列，准备抢占锁）
                msg.notify();
            }
        }


    }
}
