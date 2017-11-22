package cn.blog.utils;

import java.util.HashMap;
import java.util.Map;


public class R1 extends HashMap<String,Object>{
    //构造
    public R1(){
        put("code",0);
    }
    //错误
    public static R1 error(int code, String msg){
        R1 r1 = new R1();
        r1.put("code",code);
        r1.put("msg",msg);
        return r1;
    }
    //成功
    public static R1 success(int code,String msg){
        R1 r1 = new R1();
        r1.put("code",code);
        r1.put("msg",msg);
        return r1;
    }
    //返回对象
    public static R1 success(Map<String,Object> map){
        R1 r1 = new R1();
        r1.putAll(map);
        return r1;
    }
    //
    //public
}
