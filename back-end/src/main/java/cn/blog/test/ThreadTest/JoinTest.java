package cn.blog.test.ThreadTest;

/**
 * Created by liuwen on 2017/11/23.
 */
public class JoinTest {
    public volatile static int i=0;
    public static class JoinThread extends Thread{
        @Override
        public void run(){
            for (i=0;i<1000;i++){
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        JoinThread joinThread = new JoinThread();
        joinThread.start();
        joinThread.join();
        System.out.println(i);
    }
}
