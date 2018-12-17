package site.linyy.jdkstudy.jdk.concurrent;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierThread {

    public static void main(String[] args) {
        int n = 4;
        CyclicBarrier cyclicBarrier = new CyclicBarrier(n);
        for (int i = 0; i < n; i++) {
            new Thread(new Writer(cyclicBarrier)).start();
        }
    }

    static class Writer implements Runnable {

        private final CyclicBarrier barrier;

        public Writer(CyclicBarrier c) {
            this.barrier = c;
        }

        @Override
        public void run() {

            while (true) {
                System.out.println(
                    "线程" + Thread.currentThread().getName() + "正在写入数据。。。");
                try {
                    Thread.sleep(5000);// 模拟写入数据
                    System.out.println(
                        "线程" + Thread.currentThread().getName() + "写入数据完毕");
                    barrier.await();
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
