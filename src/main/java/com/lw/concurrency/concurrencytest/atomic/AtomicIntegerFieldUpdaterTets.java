package com.lw.concurrency.concurrencytest.atomic;

import com.lw.concurrency.annotation.ThreadSafe;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/**
 * @description: AtomicIntegerFieldUpdater：原子性更新某个类的实例的某个字段
 * 非static字段，volatile修饰
 * @author: luo.wen
 * @createTime: 2019/9/13
 */
@Slf4j
@ThreadSafe
public class AtomicIntegerFieldUpdaterTets {

    private static AtomicIntegerFieldUpdater<AtomicIntegerFieldUpdaterTets> updater =
            AtomicIntegerFieldUpdater.newUpdater(AtomicIntegerFieldUpdaterTets.class, "count");
    @Getter
    private volatile int count = 100;

    public static void main(String[] args) {
        AtomicIntegerFieldUpdaterTets example5 = new AtomicIntegerFieldUpdaterTets();

        if (updater.compareAndSet(example5, 100, 120)) {
            log.info("update success1, {}", example5.getCount());
        }

        if (updater.compareAndSet(example5, 100, 120)) {
            log.info("update success2, {}", example5.getCount());
        } else {
            log.info("update failed, {}", example5.getCount());
        }
    }
}
