package cn.blog.test.ThreadTest;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class ThreadLocalTest {
    public static ThreadLocal<ArrayList> threadLocal = new ThreadLocal<ArrayList>();
    public static ArrayList list = new ArrayList();
    public static class Demo implements Runnable {
        private int i;

        public Demo(int i) {
            this.i = i;
        }
        @Override
        public void run() {
            list.add(i);
            threadLocal.set(list);
            System.out.println(threadLocal.get());
        }
    }
    public static void main(String[] args) throws InterruptedException {
        ExecutorService es = Executors.newFixedThreadPool(5);
        for (int j = 0; j < 200; j++) {
            es.execute(new Demo(j));
        }
        Thread.sleep(3000);
        System.out.println(list.size());
        es.shutdown();
    }
}
