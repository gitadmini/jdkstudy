package site.linyy.jdkstudy.jdk.concurrent;

import java.util.concurrent.TimeUnit;

public class StudyTimeUnit {

    public static void main(String[] args) {
        values();
    }

    private static void sleep() {
        // sleep等效于Thread.sleep()
        // 优点：不用手动进行单位转化
        System.out.println("begin");
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("end");
    }

    private static void values() {
        // 返回所有枚举值的数组
        for (TimeUnit c : TimeUnit.values())
            System.out.println(c);
    }

    private static void valueOf() {
        // 名字不能写错，否则会抛出异常
        TimeUnit t = TimeUnit.valueOf("MILLISECONDS");
        System.out.println(t.name());
        System.out.println(t.toMicros(2));
    }

}
