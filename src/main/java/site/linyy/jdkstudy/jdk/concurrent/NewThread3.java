package site.linyy.jdkstudy.jdk.concurrent;

import java.util.concurrent.Callable;

public class NewThread3<String> implements Callable<String> {

    @Override
    public String call() throws Exception {
        for (int i = 0; i < 7; i++) {
            System.out.println(i);
        }
        Thread.sleep(5000);
        System.out.println("执行完毕");
        return (String) "123";
    }

}
