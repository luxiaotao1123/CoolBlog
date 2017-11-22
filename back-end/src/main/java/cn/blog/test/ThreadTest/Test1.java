package cn.blog.test.ThreadTest;

import java.util.HashMap;
import java.util.Map;

public class Test1 {


    //private static Map<Integer,Object> map = Collections.synchronizedMap(new HashMap<Integer,Object>());
    private static Map<Integer,Object> map = new HashMap<Integer,Object>();


    public Test1(){
        Thread t1 = new Thread(){
            public void run(){
                for(int i=0;i<100000;i++){
                    map.put(i,i);
                    //System.out.println("t1线程输出："+map.get(i));
                }
            }
        };
        Thread t2 = new Thread(){
            public void run(){
                for(int i=0;i<100000;i++){
                    map.put(i,i);
                    //System.out.println("t2线程输出："+map.get(i));
                }
            }
        };
        Thread t3 = new Thread(){
            public void run(){
                for(int i=0;i<100000;i++){
                    map.put(i,i);
                    //System.out.println("t1线程输出："+map.get(i));
                }
            }
        };
        Thread t4 = new Thread(){
            public void run(){
                for(int i=0;i<100000;i++){
                    map.put(i,i);
                    //System.out.println("t1线程输出："+map.get(i));
                }
            }
        };
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        try {
            t1.join();
            t2.join();
            t3.join();
            t4.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Test1();
        System.out.println("shuchu"+map.size());
    }
}
