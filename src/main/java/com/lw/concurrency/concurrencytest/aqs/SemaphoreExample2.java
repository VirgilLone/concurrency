package com.lw.concurrency.concurrencytest.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

@Slf4j
public class SemaphoreExample2 {

    private final static int threadCount = 20;

    public static void main(String[] args) throws Exception {

        ExecutorService exec = Executors.newCachedThreadPool();

        final Semaphore semaphore = new Semaphore(3);

        for (int i = 0; i < threadCount; i++) {
            final int threadNum = i;
            exec.execute(() -> {
                try {
                    // 线程池中二十个线程，二十个请求都尝试去执行，信号量让每个线程去尝试
                    // 获取许可，但此时并发数设置的3，所以只有3个线程获取到了许可，剩余
                    // 的17个线程会直接丢弃掉
                    if (semaphore.tryAcquire()) { // 尝试获取一个许可
                        test(threadNum);
                        semaphore.release(); // 释放一个许可
                    }
                    /*if (semaphore.tryAcquire(5000,TimeUnit.MILLISECONDS)) { // 尝试获取一个许可，等待5s
                        test(threadNum);
                        semaphore.release(); // 释放一个许可
                    }*/
                } catch (Exception e) {
                    log.error("exception", e);
                }
            });
        }
        exec.shutdown();
    }

    private static void test(int threadNum) throws Exception {
        log.info("{}", threadNum);
        Thread.sleep(100);
    }
}
