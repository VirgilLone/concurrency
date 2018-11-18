package com.lw.concurrency.exception;

import lombok.Getter;
import lombok.Setter;

/** 自定义业务异常类
 * Created by WYluo on 2018/11/17.
 */
@Setter
@Getter
public class BizException extends RuntimeException{
    private String code;

    public BizException(String code,String message) {
        super(message);
        this.code = code;
    }
}
