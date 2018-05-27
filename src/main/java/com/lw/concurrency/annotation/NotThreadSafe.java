package com.lw.concurrency.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by WYluo on 2018/5/27.
 */

/**
 * 用来标记该项目线程不安全的类或写法
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.SOURCE)

public @interface NotThreadSafe {

    String value() default "";
}
