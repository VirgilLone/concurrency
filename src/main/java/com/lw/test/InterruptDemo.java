package com.lw.test;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

@Slf4j
public class InterruptDemo implements Runnable{

    private int i=0;

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()){
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) { //触发线程的复位 interrupt -->false
                //此处因interrupt 响应线程的中断而报错，而不是强行中断线程
                e.printStackTrace();

                //决定如何处理这个中断的请求，此处是再次中断
                Thread.currentThread().interrupt();
            }
            log.info("线程复位之后不处理的话继续while死循环。。。");
        }
        log.info("跳出了while循环");

    }

    public static void main(String[] args) throws InterruptedException {

        Thread thread = new Thread(new InterruptDemo());
        thread.start();
        TimeUnit.SECONDS.sleep(2);
        thread.interrupt();// 共享变量interrupt -->true、唤醒处于阻塞状态的线程
    }
}
