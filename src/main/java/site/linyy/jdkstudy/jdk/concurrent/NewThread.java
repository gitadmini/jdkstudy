package site.linyy.jdkstudy.jdk.concurrent;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class NewThread {

    // 多线程实现方式主要有:
    // 1、继承Thread类创建线程
    // 2、实现Runnable接口创建线程
    // 3、实现Callable接口通过FutureTask包装器来创建Thread线程
    // 4、使用ExecutorService、Callable、Future实现有返回结果的线程
    // 5、使用ThreadPoolExcutor，推荐

    public static void main(String[] args)
            throws InterruptedException, ExecutionException {
        // new1();
        // new2();
        new3();
        // new4();
        // new4_2();
        // new5();
    }

    private static void new5() {
        // 5、线程池推荐使用ThreadPoolExcutor，能够了解线程池
        // 学习参考：http://www.cnblogs.com/sunhaoyu/articles/6955923.html

        // 线程池的使用场合
        // （1）单个任务处理的时间比较短；
        // （2）需要处理的任务数量大；

        // (1)corePoolSize： 线程池维护线程的最少数量 （core : 核心）
        // (2)maximumPoolSize： 线程池维护线程的最大数量
        // (3)keepAliveTime： 线程池维护线程所允许的空闲时间
        // (4)unit： 线程池维护线程所允许的空闲时间的单位
        // (5)workQueue： 线程池所使用的缓冲队列
        // (6)handler： 线程池对拒绝任务的处理策略

        // 通过 execute(Runnable)方法被添加到线程池，任务就是一个 Runnable类型的对象，任务的执行方法就是
        // Runnable类型对象的run()方法。
        // 当一个任务通过execute(Runnable)方法欲添加到线程池时：
        // 如果此时线程池中的数量小于corePoolSize，即使线程池中的线程都处于空闲状态，也要创建新的线程来处理被添加的任务。
        // 如果此时线程池中的数量等于 corePoolSize，但是缓冲队列 workQueue未满，那么任务被放入缓冲队列。
        // 如果此时线程池中的数量大于corePoolSize，缓冲队列workQueue满，并且线程池中的数量小于maximumPoolSize，建新的线程来处理被添加的任务。
        // 如果此时线程池中的数量大于corePoolSize，缓冲队列workQueue满，并且线程池中的数量等于maximumPoolSize，那么通过
        // handler所指定的策略来处理此任务。
        // 也就是：处理任务的优先级为：
        // 核心线程corePoolSize、任务队列workQueue、最大线程maximumPoolSize，如果三者都满了，使用handler处理被拒绝的任务。
        // 当线程池中的线程数量大于
        // corePoolSize时，如果某线程空闲时间超过keepAliveTime，线程将被终止。这样，线程池可以动态的调整池中的线程数。
        // unit可选的参数为java.util.concurrent.TimeUnit中的几个静态属性：NANOSECONDS、MICROSECONDS、MILLISECONDS、SECONDS。
        // workQueue常用的是：java.util.concurrent.ArrayBlockingQueue
        // handler有四个选择：
        // ThreadPoolExecutor.AbortPolicy()：抛出java.util.concurrent.RejectedExecutionException异常
        // ThreadPoolExecutor.CallerRunsPolicy(): 重试添加当前的任务，他会自动重复调用execute()方法
        // ThreadPoolExecutor.DiscardOldestPolicy(): 抛弃旧的任务
        // ThreadPoolExecutor.DiscardPolicy(): 抛弃当前的任务
        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(2, 4, 3,
            TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(3),
            new ThreadPoolExecutor.DiscardOldestPolicy());
        for (int i = 1; i <= 10; i++) {
            // threadPool.execute(new NewThread2()); //runnable不带返回参数
            Future<String> f = threadPool.submit(new NewThread3<String>()); // callable带返回参数
        }
        threadPool.shutdown();
    }

    // 不推荐
    private static void new4() throws InterruptedException, ExecutionException {
        // 4、使用ExecutorService、Callable、Future实现有返回结果的线程
        // 使用Excutors创建容易堆积线程，耗尽资源，造成OOM

        // newCachedThreadPool工作线程的创建数量几乎没有限制,如果工作线程空闲了指定的时间(默认为1分钟)，则该工作线程将自动终止。

        // FixedThreadPool是一个典型且优秀的线程池，它具有线程池提高程序效率和节省创建线程时所耗的开销的优点。
        // 但是，在线程池空闲时，即线程池中没有可运行任务时，它不会释放工作线程，还会占用一定的系统资源。

        // newSingleThreadExecutor只创建唯一的工作者线程来执行任务，保证所有任务按照指定顺序执行。如果这个线程异常结束，会有另一个取代它，保证顺序执行。

        // newScheduleThreadPool创建一个定长的线程池，而且支持定时的以及周期性的任务执行，支持定时及周期性任务执行。

        int size = 2;
        ExecutorService pool = Executors.newFixedThreadPool(size);
        for (int i = 0; i < size; i++) {
            Callable<String> c = new NewThread3<>();
            Future<String> f = pool.submit(c);
            System.out.println(">>>" + f.get());
        }
        pool.shutdown();

    }

    // 不推荐
    private static void new4_2() {

        ScheduledExecutorService scheduledThreadPool = Executors
            .newScheduledThreadPool(5);
        // schedule延迟一段时间执行
        scheduledThreadPool.schedule(new Runnable() {
            public void run() {
                System.out.println("delay 3 seconds");
            }
        }, 3, TimeUnit.SECONDS);
        // scheduleAtFixedRate循环执行，当线程执行完毕后，延迟一段时间后会再次执行
        scheduledThreadPool.scheduleAtFixedRate(new Runnable() {
            public void run() {
                System.out.println("delay 3 seconds");
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                }
            }
        }, 3, 4, TimeUnit.SECONDS);
    }

    private static void new3() {
        // 3、实现Callable接口通过FutureTask包装器来创建Thread线程
        // 这种可以拿到线程中的状态
        Callable<String> c = new NewThread3<>();
        FutureTask<String> f = new FutureTask<>(c);
        Thread t = new Thread(f);
        t.start();
        try {
            String str = f.get(2, TimeUnit.SECONDS); // 超时未拿到，则抛出异常
            System.out.println(str);
        } catch (Exception e) {
            System.out.println("not yet");
            e.printStackTrace();
        }
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
