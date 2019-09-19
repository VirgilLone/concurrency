package com.lw.thread;

import java.util.Date;
import java.util.concurrent.Callable;

/**
 * @description:
 * @author: luo.wen
 * @createTime: 2019/9/10
 */
public class ImportThread_callable implements Callable {

    private String taskNum;

    public ImportThread_callable(String taskNum) {
        this.taskNum = taskNum;
    }

    @Override
    public Object call() throws Exception {
        System.out.println(">>>" + taskNum + "任务启动");
        Date dateTmp1 = new Date();
        Thread.sleep(1000);
        Date dateTmp2 = new Date();
        long time = dateTmp2.getTime() - dateTmp1.getTime();
        System.out.println(">>>" + taskNum + "任务终止");
        return taskNum +"("+Thread.currentThread().getName()+")" +"任务返回运行结果,当前任务时间【" + time + "毫秒】";
    }
}
