package com.lw.concurrency.controller;

import com.lw.concurrency.exception.BizException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by WYluo on 2018/5/27.
 */
@RestController
public class TestController {

    @GetMapping(value = "/test")
    @ResponseBody
    public String test(String x){
        if("xxx".equals(x)){
            throw new BizException("-1","异常！全局处理我这个异常把");
        }
        return "test...."+x;
    }
}
