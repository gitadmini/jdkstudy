package site.linyy.jdkstudy.jdk.concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

public class NewThread {

    // 多线程实现方式主要有四种:
    // 1、继承Thread类创建线程
    // 2、实现Runnable接口创建线程
    // 3、实现Callable接口通过FutureTask包装器来创建Thread线程
    // 4、使用ExecutorService、Callable、Future实现有返回结果的线程

    public static void main(String[] args)
            throws InterruptedException, ExecutionException {
        // new1();
        // new2();
        // new3();
        new4();
    }

    private static void new4() throws InterruptedException, ExecutionException {
        // 4、使用ExecutorService、Callable、Future实现有返回结果的线程
        int size = 2;
        ExecutorService pool = Executors.newFixedThreadPool(size);
        List<Future<String>> list = new ArrayList<Future<String>>();
        for (int i = 0; i < size; i++) {
            Callable<String> c = new NewThread3<>();
            Future<String> f = pool.submit(c);
            list.add(f);
        }
        pool.shutdown();

        for (Future<String> f : list) {
            System.out.println(">>>" + f.get());
        }
    }

    private static void new3() {
        // 3、实现Callable接口通过FutureTask包装器来创建Thread线程
        // 这种可以拿到线程中的状态
        Callable<String> c = new NewThread3<>();
        FutureTask<String> f = new FutureTask<>(c);
        Thread t = new Thread(f);
        t.start();
    }

    private static void new2() {
        // 2、实现Runnable接口创建线程
        // 这种还可以继承其他类，扩展性比1好
        NewThread2 r = new NewThread2();
        Thread t = new Thread(r);
        t.start();
    }

    private static void new1() {
        // 1、继承Thread类创建线程
        NewThread1 myThread1 = new NewThread1();
        myThread1.start();
    }

}
