package cn.blog.test.ThreadTest;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public class AtomicTest {
    public static AtomicInteger atomicInteger = new AtomicInteger();
    //public static AtomicReference atomicReference = new AtomicReference();
    public static class Demo implements Runnable{

        @Override
        public void run() {
            for (int j=0;j<1000;j++){
                atomicInteger.incrementAndGet();        //当前值加1并且返回当前值
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ExecutorService es = Executors.newFixedThreadPool(10);
        for (int i =0;i<10;i++){
            es.submit(new Demo());
        }
        Thread.sleep(5000);
        System.out.println(atomicInteger);
    }
}
