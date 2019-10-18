package com.lw.concurrency.concurrencytest.future_enhance;

import java.util.function.Supplier;

/**
 * @description:
 * @author: luo.wen
 * @createTime: 2019/10/17
 */
public class MyThreadt333 implements Supplier {

    private String commandstr;          // 要运行的mingling
    public MyThreadt333(String commandstr) {
        this.commandstr = commandstr;
    }

    @Override
    public Object get() {
        int sum = 0;
        for (int i = 0; i < 30; i++) {
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            sum += i;
            System.out.println("Mythread333: "+i);
        }
        return String.valueOf(sum+300000);
    }
}
