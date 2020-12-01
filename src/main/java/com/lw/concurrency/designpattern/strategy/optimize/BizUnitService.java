package com.lw.concurrency.designpattern.strategy.optimize;

import org.springframework.stereotype.Service;

/**
 * @description:
 * @author: luo.wen
 * @createTime: 2020/7/3
 */
@Service
public class BizUnitService {

    public String bizOne(String order) {
        return order + "各种花式操作1";
    }
    public String bizTwo(String order) {
        return order + "各种花式操作2";
    }
    public String bizThree(String order) {
        return order + "各种花式操作3";
    }

}
