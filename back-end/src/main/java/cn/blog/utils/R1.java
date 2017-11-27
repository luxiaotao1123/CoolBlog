package cn.blog.utils;

import java.util.HashMap;
import java.util.Map;


public class R1 extends HashMap<String,Object>{
    //构造
    public R1(){
        put("code",200);
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
    //返回多个对象存进map
    public static R1 ok(Map<String,Object> map){
        R1 r1 = new R1();
        r1.putAll(map);
        return r1;
    }
    //
    //返回一个对象存进map，key为对象名，key自己命名
    public static R1 add(String key,Object value){
        R1 r1 = new R1();
        r1.put(key,value);
        r1.ok(r1);
        return r1;
    }
}
