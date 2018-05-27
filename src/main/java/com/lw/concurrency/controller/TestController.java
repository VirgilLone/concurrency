package com.lw.concurrency.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by WYluo on 2018/5/27.
 */
@Controller
public class TestController {

    @RequestMapping("/test")
    @ResponseBody
    public String test(){
        return "test....";
    }
}
