package cn.blog.test.ThreadTest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by liuwen on 2017/11/22.
 */
public class WaitAndNotify {

    private static Map<String,Object> map = new HashMap<String,Object>();

    public static class T1 implements Runnable{
        public void run(){
            synchronized (map) {
                Thread t1 = new Thread() {
                    public synchronized void run() {
                        System.out.println("go1");
                        try {
                            System.out.println("go1进入wait");
                            map.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println("go1back");
                    }
                };
                t1.start();
            }

        }
    }

    public static class T2 implements Runnable{
        public void run(){
            synchronized (map){
                Thread t1 = new Thread(){
                    public synchronized void run(){
                        System.out.println("go2");
                        System.out.println("go2要释放go1了");
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        map.notify();
                        System.out.println("go1继续了");
                    }
                };
                t1.start();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        WaitAndNotify waitAndNotify = new WaitAndNotify();
        T1 t1 = new T1();
        T2 t2 = new T2();
        t1.run();
        t2.run();

    }
}
