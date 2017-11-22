package cn.blog.test.ThreadTest;

/**
 * Created by liuwen on 2017/11/22.
 */
public class WaitAndNotify {

    public synchronized void t1(){
        Thread t1 = new Thread(){
            public void run(){
                while (true){
                    System.out.println("go");
                    if (Thread.currentThread().isInterrupted()){
                        break;
                    }
                }
            }
        };
        t1.start();
    }

    /*public WaitAndNotify() {
        Thread t1 = new Thread(){
            public void run(){
                while (true){
                    System.out.println("go");
                    if (Thread.currentThread().isInterrupted()){
                        break;
                    }
                }
            }
        };
        t1.start();
    }*/

    public static void main(String[] args) throws InterruptedException {
        WaitAndNotify waitAndNotify = new WaitAndNotify();
        Thread.sleep(5000);
        waitAndNotify.t1();
    }
}
