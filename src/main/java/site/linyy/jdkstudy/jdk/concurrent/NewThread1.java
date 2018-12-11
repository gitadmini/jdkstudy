package site.linyy.jdkstudy.jdk.concurrent;

public class NewThread1 extends Thread {

    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println(i);
        }
    }

}
