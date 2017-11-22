package cn.blog.test;

import java.util.Stack;

/**
 * Created by liuwen on 2017/11/20.
 */
public class test4 {
    public static void main(String[] args) {
        String value = "123456789";
        Stack stack = new Stack();
        StringBuffer result =new StringBuffer();

        for (char c:value.toCharArray()){
            stack.push(c);
        }
        while (!stack.empty()){
            result.append(stack.pop());
        }
        System.out.printf(result.toString());
    }
}
