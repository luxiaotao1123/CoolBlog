package cn.blog.test.ThreadTest.MyDisruptor;

import com.lmax.disruptor.EventFactory;

public class DataFactory implements EventFactory{
    @Override
    public Object newInstance() {
        return new MyData();
    }
}
