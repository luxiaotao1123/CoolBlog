package cn.blog.test.ThreadTest;

/**
 * Created by liuwen on 2017/11/23.
 */
public class DaemonTest {

    public static class DaemonT extends Thread{
        public void run(){
            while (true){
                System.out.println("我还在");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                }
            }
        }
    }


    public static void main(String[] args) throws InterruptedException {
        DaemonT daemonT = new DaemonT();
        daemonT.setPriority(10);
        daemonT.setDaemon(true);
        daemonT.start();
        Thread.sleep(2000);
    }
}
