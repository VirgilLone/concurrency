package com.lw.concurrency.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by WYluo on 2018/5/27.
 */

/**
 * 用来标记该项目不推荐的类或写法
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.SOURCE)

public @interface NotRecommend {

    String value() default "";
}
