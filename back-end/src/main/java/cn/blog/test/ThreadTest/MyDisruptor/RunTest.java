package cn.blog.test.ThreadTest.MyDisruptor;

import com.lmax.disruptor.BlockingWaitStrategy;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;

import java.nio.ByteBuffer;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;


public class RunTest {
    public static void main(String[] args) throws InterruptedException {
        Executor executor = Executors.newCachedThreadPool();        //创建线程池
        DataFactory dataFactory = new DataFactory();        //创建Factory实例
        int bufferSize = 1024;      //设置缓存区大小为1024（必须是2的整数次幂）
        Disruptor<MyData> disruptor = new Disruptor<MyData>(
                dataFactory,
                bufferSize,
                executor,
                ProducerType.MULTI,
                new BlockingWaitStrategy()
                );
        disruptor.handleEventsWithWorkerPool(
                new Consumers(),
                new Consumers(),
                new Consumers(),
                new Consumers()
                );
        disruptor.start();      //Disruptor启动
        RingBuffer<MyData> ringBuffer = disruptor.getRingBuffer();      //实例化环形队列并与Disruptor绑定
        Producers producers = new Producers(ringBuffer);        //实例化生产者并绑定ringBuffer
        ByteBuffer byteBuffe = ByteBuffer.allocate(8);      //
        for (long n = 0;true;n++){
            byteBuffe.putLong(0,n);
            producers.putData(byteBuffe);
            Thread.sleep(100);
            System.out.println("add data "+n);
        }
    }
}
