package com.lw.concurrency.concurrencytest.atomic;

import com.lw.concurrency.annotation.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @description:
 * @author: luo.wen
 * @createTime: 2019/9/13
 */
@Slf4j
@ThreadSafe
public class AtomicReferenceTest {

    private static AtomicReference<Integer> intflag = new AtomicReference<>(0);

    private static AtomicBoolean atomicBoolean = new AtomicBoolean(false);

    public static void main(String[] args) {
        intflag.compareAndSet(0,2);
        intflag.compareAndSet(0,1);
        intflag.compareAndSet(1,3);
        intflag.compareAndSet(2,4);
        intflag.compareAndSet(3,5);

        log.info("intflag--->{}",intflag.get());


        atomicBoolean.compareAndSet(false,true);
        log.info("atomicBoolean--->{}",atomicBoolean.get());

    }
}
