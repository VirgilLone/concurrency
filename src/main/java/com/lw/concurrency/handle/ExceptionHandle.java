package com.lw.concurrency.handle;

import com.lw.concurrency.AopTest.ServerResponse;
import com.lw.concurrency.exception.BizException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/** ControllerAdvice全局异常处理
 * Created by WYluo on 2018/11/17.
 */
@Slf4j
@ControllerAdvice
public class ExceptionHandle {

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ServerResponse handle(Exception e){
        if(e instanceof BizException){
            BizException bizException= (BizException) e;
            log.error("【系统异常1】{}",bizException.getMessage());
            return ServerResponse.fail(bizException.getCode(),bizException.getMessage());
        }else{
            log.error("【系统异常】{}",e);
            return ServerResponse.fail("-1","未知错误！");
        }
    }

}
