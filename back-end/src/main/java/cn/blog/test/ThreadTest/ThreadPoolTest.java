package cn.blog.test.ThreadTest;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.*;

/**
 * Created by liuwen on 2017/11/28.
 */
public class ThreadPoolTest {

    public static class TestThread implements Runnable{
        @Override
        public void run() {
            System.out.println(System.currentTimeMillis()+"线程ID："+Thread.currentThread().getId());
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        TestThread testThread = new TestThread();
        ExecutorService es = new ThreadPoolExecutor(5,5,0L, TimeUnit.SECONDS,
                                                    new ArrayBlockingQueue<Runnable>(10), Executors.defaultThreadFactory(),
                                                    new RejectedExecutionHandler(){
                                                        @Override
                                                        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                                                            System.out.println("等待线程被拒绝");
                                                        }
                                                    }){
            @Override
            protected void beforeExecute(Thread t,Runnable r){
                System.out.println("前");
            }
            @Override
            protected void afterExecute(Runnable r,Throwable t){
                System.out.println("后");
            }
            @Override
            protected  void terminated(){
                System.out.println("线程池退出");
            }
        };
        for (int i=0;i<Integer.MAX_VALUE;i++){
            es.submit(testThread);
            Thread.sleep(10);
        }
        Thread.sleep(5000);
        es.shutdown();
        Map map = Collections.synchronizedMap(new HashMap<String,Object>());
    }
}
