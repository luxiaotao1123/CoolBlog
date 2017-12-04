package cn.blog.test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class test3{
    private test3(){}       //外部无法再通过new得到该实例
    private static test3 test = new test3();    //private保证安全性，设置test为null，static为了配合下面的getInstance函数
    public static test3 getInstance(){      //public使外部能使用此方法，static能直接使用此方法
        return test;
    }
}

