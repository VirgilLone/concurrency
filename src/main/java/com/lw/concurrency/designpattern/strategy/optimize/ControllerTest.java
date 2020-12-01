package com.lw.concurrency.designpattern.strategy.optimize;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description:
 * @author: luo.wen
 * @createTime: 2020/7/3
 */
@RestController
public class ControllerTest {

    @Autowired
    private BizService bizService;

    @PostMapping("/biz/testMuti")
    public String test1(String order, Integer level) {
        return bizService.getCheckResultComX(order, level);
    }

}
