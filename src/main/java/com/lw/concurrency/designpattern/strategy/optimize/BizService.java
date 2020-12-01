package com.lw.concurrency.designpattern.strategy.optimize;

import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * @description: 某个业务服务类
 * 原始策略模式的缺点：1.策略类会越写越多；2.业务逻辑分散到各个实现类中，而且没有一个地方可以俯视整个业务逻辑
 * 策略模式的优化，避免了上述两个缺点
 * @author: luo.wen
 * @createTime: 2020/7/3
 */
@Service
public class BizService {

    private final BizUnitService bizUnitService;
    public BizService(BizUnitService bizUnitService) {
        this.bizUnitService = bizUnitService;
    }

    /**
     * 把本地map换成redis，动态增加策略 是否在生产可行？？
     */
    private Map<String, Function<String, String>> resultDispatcher = new HashMap<>();

    /**
     * 替代if/else
     * 初始化 业务逻辑分派Map 其中value 存放的是 函数式接口
     */
    @PostConstruct
    public void checkResultDispatcherComXInit() {
        resultDispatcher.put("key_订单1", bizUnitService::bizOne);
        resultDispatcher.put("key_订单1_订单2", bizUnitService::bizTwo);
        resultDispatcher.put("key_订单1_订单2_订单3", order -> bizUnitService.bizThree(order));
    }

    public String getCheckResultComX(String order, int level) {

        String ley = getDispatcherComXKey(order, level);

        Function<String, String> result = resultDispatcher.get(ley);
        if (result != null) {
            // 执行这段表达式获得String类型的结果
            return result.apply(order);
        }
        return "不在处理的逻辑中返回业务错误";
    }

    /**
     * 生成key的逻辑
     */
    private String getDispatcherComXKey(String order, int level) {
        StringBuilder key = new StringBuilder("key");
        for (int i = 1; i <= level; i++) {
            key.append("_" + order + i);
        }
        return key.toString();
    }


}
