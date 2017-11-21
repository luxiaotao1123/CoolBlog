package cn.blog.test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by liuwen on 2017/11/20.
 */
public class test3 implements Runnable{

    public Map<String,Object> test(){
        Map<String,Object> hashmap = new HashMap<String,Object>();
        hashmap.put("1","1");
        hashmap.put("1","2");
        System.out.println(hashmap.toString()+new Date());
        return hashmap;
    }

    public static void main(String[] args) throws InterruptedException {
        test3 test3 =new test3();
        new Thread(test3).start();
        Thread.sleep(10000);
        new Thread(test3).start();
    }

    @Override
    public void run() {
        test();
    }
}
