package com.lw.concurrency.concurrencytest.lock;

import com.google.common.collect.Lists;

import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ProducerConsumerByLockTest {

    public static void main(String[] args) {
        Queue<String> queue =  Lists.newLinkedList();
        int maxSize = 5;

        Lock lock = new ReentrantLock();
        Condition condition = lock.newCondition();

        ProducerByLock producer = new ProducerByLock(queue, maxSize, lock, condition);
        ConsumerByLock consumer = new ConsumerByLock(queue, maxSize, lock, condition);
        Consumer2ByLock consumer2 = new Consumer2ByLock(queue, maxSize, lock, condition);

        Thread thread1 = new Thread(producer);
        Thread thread2 = new Thread(consumer);
        Thread thread3 = new Thread(consumer2);
        thread1.start();
        thread2.start();
        thread3.start();

    }
}
