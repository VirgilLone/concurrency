package com.lw.test;

import com.google.common.collect.Lists;

import java.util.Queue;

public class ProducerConsumerTest {

    public static void main(String[] args) {
        Queue<String> queue =  Lists.newLinkedList();
        int maxSize = 5;

        Producer producer = new Producer(queue, maxSize);
        Consumer consumer = new Consumer(queue, maxSize);

        Thread thread1 = new Thread(producer);
        Thread thread2 = new Thread(consumer);
        thread1.start();
        thread2.start();

    }
}
