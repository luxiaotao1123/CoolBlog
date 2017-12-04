package cn.blog.test.ThreadTest.MyDisruptor;


import com.lmax.disruptor.RingBuffer;

import java.nio.ByteBuffer;

public class Producers {
    private final RingBuffer<MyData> ringBuffer;        //创建环形队列(环形缓冲区)

    public Producers(RingBuffer<MyData> ringBuffer) {
        this.ringBuffer = ringBuffer;           //将ringBuffer与Producers绑定
    }

    public void putData(ByteBuffer byteBuffer){         //此方法将产生的数据推入缓冲区

        long sequeue = ringBuffer.next();       //通过.next()方法得到ringBuffer的下一个节点，并且赋值给sequeue

        MyData event = ringBuffer.get(sequeue);     //将mydata数据存入到下一个节点

        event.setValue(byteBuffer.getLong(0));        //mydata的值有ByteBuffer参数带入

        ringBuffer.publish(sequeue);        //将sequeue节点内的数据发布
    }
}
