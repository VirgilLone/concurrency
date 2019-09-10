package com.lw.thread;

/**
 * @description:
 * @author: luo.wen
 * @createTime: 2019/9/10
 */
public class MyCountDown {
    private int count;

    public MyCountDown(int count) {
        this.count = count;
    }

    public synchronized void countDown() {
        count--;
    }

    public synchronized boolean hasNext() {
        return (count > 0);
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
