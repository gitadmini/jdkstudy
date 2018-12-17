package site.linyy.jdkstudy.jdk.concurrent;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchThread implements Runnable {

    private final CountDownLatch countDownLatch1;

    private final CountDownLatch countDownLatch2;

    private final CountDownLatch countDownLatch3;

    private final int threadNum;

    public CountDownLatchThread(int tNum, CountDownLatch c1, CountDownLatch c2,
            CountDownLatch c3) {
        this.countDownLatch1 = c1;
        this.countDownLatch2 = c2;
        this.countDownLatch3 = c3;
        this.threadNum = tNum;
    }

    @Override
    public void run() {

        try {
            countDownLatch1.await(); // 阻塞，等待外部countDown
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("第一步，同时开始工作，当前线程：" + threadNum);

        countDownLatch2.countDown();

        countDownLatch3.countDown();

    }

}
