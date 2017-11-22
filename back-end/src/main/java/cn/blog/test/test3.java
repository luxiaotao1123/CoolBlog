package cn.blog.test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by liuwen on 2017/11/20.
 */
public class test3 implements Runnable{

    private StringBuilder value=null;



    public static void main(String[] args) throws InterruptedException {
        test3 test3 =new test3();
        new Thread(test3).start();
        new Thread(test3).start();
    }

    @Override
    public void run() {
        int i = 1;
        run2();
        System.out.println(i);
    }

    public void run2(){
        int i = 2;
        System.out.println(i);
    }

}
