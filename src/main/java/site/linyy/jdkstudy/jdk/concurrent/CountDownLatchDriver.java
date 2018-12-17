package site.linyy.jdkstudy.jdk.concurrent;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchDriver {

    public static void main(String[] args) throws InterruptedException {

        int n = 10;
        CountDownLatch c1 = new CountDownLatch(1);
        CountDownLatch c2 = new CountDownLatch(n);
        CountDownLatch c3 = new CountDownLatch(n);
        for (int i = 0; i < 10; i++) {
            new Thread(new CountDownLatchThread(i, c1, c2, c3)).start();
        }
        System.out.println("线程开始前，先做点事情");
        c1.countDown();
        c2.await();
        System.out.println("第二步完成了");
        c3.await();
        System.out.println("线程全部结束了");
    }

}
