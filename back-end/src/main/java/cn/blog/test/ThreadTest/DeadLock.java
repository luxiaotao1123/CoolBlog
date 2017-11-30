package cn.blog.test.ThreadTest;

import java.util.concurrent.atomic.AtomicReferenceArray;

/**
 * Created by liuwen on 2017/11/29.
 */
public class DeadLock extends Thread{
    protected String suo;
    public static String zuo = new String();
    public static String you = new String();

    public DeadLock(String suo){
        this.suo=suo;
    }

    @Override
    public void run(){
        if (suo==zuo){
            synchronized (zuo){
                System.out.println("拿到了左，正在拿右......");
                synchronized (you){
                    System.out.println("拿到了右，成功了");
                }
            }
        }
        if (suo==you){
            synchronized (you){
                System.out.println("拿到了右，正在拿左......");
                synchronized (zuo){
                    System.out.println("拿到了zuo，成功了");
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        for (int i=0;i<10000;i++){
            DeadLock t1 = new DeadLock(zuo);
            DeadLock t2 = new DeadLock(you);
            t1.start();t2.start();
        }

        Thread.sleep(50000);
    }
}
