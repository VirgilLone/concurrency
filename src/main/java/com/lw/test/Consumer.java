package com.lw.test;

import java.util.Queue;
import java.util.concurrent.TimeUnit;

public class Consumer implements Runnable{

    private Queue<String> msg;
    private int maxSize;

    public Consumer(Queue<String> msg, int maxSize) {
        this.msg = msg;
        this.maxSize = maxSize;
    }

    @Override
    public void run() {
        while (true){
            synchronized (msg){
                while (msg.isEmpty()){
                    try {
                        msg.wait();
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
                msg.notify();
            }

        }

    }
}
