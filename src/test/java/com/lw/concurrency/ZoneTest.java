package com.lw.concurrency;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
public class ZoneTest {

    @Test
    public void java8Zone() {
        System.out.println(LocalDateTime.now());
        String stringDate = "2021-11-15 09:00:00";

        // 初始化三个时区
        ZoneId timeZoneSH = ZoneId.of("Asia/Shanghai");
        ZoneId timeZoneNY = ZoneId.of("America/New_York");
//        ZoneId timeZoneTK = ZoneId.of("Asia/Tokyo");
        ZoneId timeZoneTK = ZoneOffset.ofHours(9);// 不建议使用，偏移量可能会随着夏时令变化

        // 格式化器
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        ZonedDateTime zonedDateTime = ZonedDateTime.of(LocalDateTime.parse(stringDate, dateTimeFormatter), timeZoneSH);

        // 使用DateTimeFormatter格式化时间，可以通过withZone方法直接设置格式化使用的时区
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss Z");
        log.info(timeZoneSH.getId() + "======" + outputFormatter.withZone(timeZoneSH).format(zonedDateTime));
        log.info(timeZoneNY.getId() + "======" + outputFormatter.withZone(timeZoneNY).format(zonedDateTime));
        log.info(timeZoneTK.getId() + "======" + outputFormatter.withZone(timeZoneTK).format(zonedDateTime));


        Calendar calendar = Calendar.getInstance();
        calendar.set(2021, Calendar.NOVEMBER, 15, 11, 45, 19);
        System.out.println(calendar.getTime());

        Calendar calendar2 = Calendar.getInstance(TimeZone.getTimeZone("America/New_York"));
        calendar2.set(2021, Calendar.NOVEMBER, 15, 11, 45, 19);
        System.out.println(calendar2.getTime());
        System.out.println("-----------------------");

        // Date转为LocalDateTime。要先转为Instant得到UTC时间戳，并需要提供当前的时区
        // Date--->Instant--->LocalDateTime
        Date in = new Date();
        System.out.println(in);
        LocalDateTime ldt = LocalDateTime.ofInstant(in.toInstant(), ZoneId.systemDefault());
        System.out.println(ldt);
        // LocalDateTime转为Date。也需要提供时区，用于指定是哪个时区的时间表示，转换为ZonedDateTime，然后才能获得 UTC 时间戳
        // LocalDateTime--->ZonedDateTime--->Instant--->Date
        Date out = Date.from(ldt.atZone(ZoneId.systemDefault()).toInstant());
        System.out.println(out);
        System.out.println("-----------------------");

        // 假设拿到了一个时间2021-10-15 15:18:03.230666，该时间是北京时间
        String dataStr = "2021-10-15 15:18:03.230666";
        DateTimeFormatter DTF = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSSSS", Locale.SIMPLIFIED_CHINESE);
        LocalDateTime localDateTime=LocalDateTime.parse(dataStr,DTF);
        System.out.println(localDateTime.atZone(ZoneId.of("Asia/Shanghai")).toInstant().toEpochMilli());// 1634282283230  得到东八区时间戳(上海)
//        System.out.println(localDateTime.toInstant(ZoneOffset.of("+8")).toEpochMilli());
        System.out.println(localDateTime.atZone(ZoneId.of("Asia/Tokyo")).toInstant().toEpochMilli());// 1634278683230  得到东九区(东京)时间戳

        // 在东九区恢复该时间戳 1634282283230，用来表示这一时刻的东京时间   ZoneId.of("Asia/Tokyo")  ZoneOffset.ofHours(+9)
        System.out.println(LocalDateTime.ofInstant(Instant.ofEpochMilli(1634282283230L), ZoneId.of("Asia/Tokyo")).format(DTF));

    }

}
