package com.lw.concurrency.concurrencytest.publish;

import com.lw.concurrency.annotation.NotRecommend;
import com.lw.concurrency.annotation.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;

/**
 * @description: 对象未完成构造之前不能将其发布
 * @author: luo.wen
 * @createTime: 2019/9/15
 */
@Slf4j
@NotThreadSafe
@NotRecommend
public class Escape {

    private int thisCanBeEscape = 0;

    public Escape () {
        new InnerClass();
    }

    private class InnerClass {

        public InnerClass() {
            log.info("----->{}", Escape.this.thisCanBeEscape);
        }
    }

    public static void main(String[] args) {
        new Escape();
    }

}
