package site.linyy.jdkstudy.jdk.concurrent;

public class NewThread2 implements Runnable {

    @Override
    public void run() {
        for (int i = 0; i < 3; i++) {
            System.out.println(i);
        }
    }

}
