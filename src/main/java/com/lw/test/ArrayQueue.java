package com.lw.test;

/**
 * 数组实现的简单有界队列
 */
public class ArrayQueue {

    public static void main(String[] args) {
        ArrayQueue arrayQueue = new ArrayQueue(8);
        arrayQueue.offer(4);
        arrayQueue.offer(3);
        arrayQueue.offer(5);
        arrayQueue.offer(8);
        System.out.println(arrayQueue.size);
        arrayQueue.poll();
        System.out.println(arrayQueue.size);

    }

    private int[] array;
    private int offerIndex;//入指针，指向下一个入队列的位置
    private int pollIndex;//出指针，指向下一个出队列的位置
    private int size;

    public ArrayQueue(int capacity) {
        this.array = new int[capacity];
        this.offerIndex = this.pollIndex = 0;
        this.size = 0;
    }

    boolean offer(Integer value) {
        if (value == null) {
            throw new NullPointerException();
        }

        if (size == array.length) {
            return false;
        }

        array[offerIndex] = value;
        offerIndex = (offerIndex + 1) % array.length;

        size++;

        return true;
    }

    Integer poll() {
        if (size == 0) {
            return null;
        }

        int value = array[pollIndex];
        pollIndex = (pollIndex + 1) % array.length;

        size--;

        return value;
    }
}
