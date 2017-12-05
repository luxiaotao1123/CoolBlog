package cn.blog.test.MathTest;


public class FibonacciTest {
    public static void main(String[] args) {
        for (int i=1;true;i++){ System.out.println(test(i)); }
    }
    public static int test(int i){
        if (i<2){ return i; }
        int j = test(i-1)+test(i-2);
        return j;
    }
}
