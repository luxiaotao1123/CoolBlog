package cn.blog.test;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ReflectTest {

    static class myTest{
        public myTest(){
            StringBuffer stringBuffer = new StringBuffer("hello");
            stringBuffer.append(",word");
            System.out.println(stringBuffer);
        }
    }

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchFieldException {
        ReflectTest reflectTest = new ReflectTest();
        new myTest();
        Class c1 = ReflectTest.class;
        Object object = c1.newInstance();
        System.out.println(object);
        Method[] field = ReflectTest.class.getDeclaredMethods();
        System.out.println(field);
    }

    public int run(){
        int i=0;
        return i;
    }
}
