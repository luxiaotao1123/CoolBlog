package cn.blog.test.ThreadTest;

/**
 * Created by liuwen on 2017/11/22.
 */
public class interruptTest {

    public static void main(String[] args) throws InterruptedException {
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
        Thread.sleep(10001);
        t1.interrupt();
    }
}
