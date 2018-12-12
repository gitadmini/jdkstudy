package site.linyy.jdkstudy.jdk.concurrent;

import java.util.concurrent.TimeUnit;

public class ThreadConstant {

    {
        // 线程的优先级(1-10)
        int a1 = Thread.MIN_PRIORITY;// 1
        int a2 = Thread.NORM_PRIORITY;// 5 默认值
        int a3 = Thread.MAX_PRIORITY;// 10
    }

    {
        // 时间单位
        Enum<TimeUnit> a1 = TimeUnit.DAYS;
        Enum<TimeUnit> a2 = TimeUnit.HOURS;
        Enum<TimeUnit> a3 = TimeUnit.MICROSECONDS; // 微秒，1毫秒=1000微秒
        Enum<TimeUnit> a4 = TimeUnit.MILLISECONDS; // 毫秒
        Enum<TimeUnit> a5 = TimeUnit.MINUTES;
        Enum<TimeUnit> a6 = TimeUnit.NANOSECONDS; // 纳秒 1微秒=1000纳秒
        Enum<TimeUnit> a7 = TimeUnit.SECONDS;
    }

}
