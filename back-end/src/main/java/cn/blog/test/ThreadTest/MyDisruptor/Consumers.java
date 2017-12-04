package cn.blog.test.ThreadTest.MyDisruptor;

import com.lmax.disruptor.WorkHandler;

/**
 * 消费者
 */
public class Consumers implements WorkHandler<MyData>{

    @Override
    public void onEvent(MyData myData) throws Exception {
        System.out.println("当前线程为:"+Thread.currentThread().getId()+"线程，它处理的数据是："+myData.getValue());
    }
}
