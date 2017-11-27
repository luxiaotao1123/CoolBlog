package cn.blog.test.ThreadTest;

import java.util.concurrent.locks.LockSupport;

public class SupportLock {
    private static Object object = new Object();
    private static SupportLock demo = new SupportLock();
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(){
            public void run(){
                synchronized (object){
                    try {
                        System.out.println("线程1开始");
                        LockSupport.park();         //挂起线程
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("线程1唤醒");
                }
            }
        };
        t1.start();
        LockSupport.unpark(t1);
    }
}
