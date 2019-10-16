package com.lw.thread;

import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.concurrent.Callable;

/**
 * @description:
 * @author: luo.wen
 * @createTime: 2019/9/10
 */
@Slf4j
public class ImportThread_callable implements Callable {

    private String taskNum;

    public ImportThread_callable(String taskNum) {
        this.taskNum = taskNum;
    }

    @Override
    public Object call() throws Exception {
        log.info(">>>{}任务启动...",taskNum);
        Date dateTmp1 = new Date();
        Thread.sleep(5000);
        Date dateTmp2 = new Date();
        long time = dateTmp2.getTime() - dateTmp1.getTime();
        log.info(">>>{}任务完成",taskNum);
        return taskNum +"("+Thread.currentThread().getName()+")" +"任务返回运行结果,当前任务时间【" + time + "毫秒】";
    }
}
