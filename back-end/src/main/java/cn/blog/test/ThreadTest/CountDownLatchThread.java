package cn.blog.test.ThreadTest;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class CountDownLatchThread implements Runnable{
    private static CountDownLatch countDownLatch = new CountDownLatch(10);
    private static CountDownLatchThread countDownLatchThread = new CountDownLatchThread();

    @Override
    public void run() {
        try {
            Thread.sleep(1000);
            System.out.println("此线程工作完成");
            countDownLatch.countDown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);    //创建一个固定大小为10的线程池
        for (int i=0;i<10;i++){
            executorService.submit(countDownLatchThread);
        }
        countDownLatch.await();
        System.out.println("10个任务完成");
        executorService.shutdown();
    }
}
