package com.lw.concurrency.concurrencytest.aqs;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @description:
 * @author: luo.wen
 * @createTime: 2019/9/24
 */
@Component
public class TimerTest implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(System.currentTimeMillis()+"<-----"+Thread.currentThread().getName());
            }
        }, new Date(), 2000);
    }
}
