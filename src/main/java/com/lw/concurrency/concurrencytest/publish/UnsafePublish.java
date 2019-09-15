package com.lw.concurrency.concurrencytest.publish;

import com.lw.concurrency.annotation.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

/**
 * @description: 线程不安全
 * @author: luo.wen
 * @createTime: 2019/9/14
 */
@Slf4j
@NotThreadSafe
public class UnsafePublish {
    private String [] state = {"a","b","c"};

    public String [] getState(){
        return state;
    }

    public static void main(String[] args) {
        UnsafePublish unsafePublish = new UnsafePublish();
        log.info("{}", Arrays.toString( unsafePublish.getState()));

        unsafePublish.getState()[0] = "d";
        log.info("{}", Arrays.toString( unsafePublish.getState()));
    }

}
