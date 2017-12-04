package cn.blog.test.MathTest;


public class FibonacciTest {
    public static void main(String[] args) {
        for (int i=1;i<10;i++){
            System.out.println(test(i));
        }
    }
    public static int test(int i){
        if (i==1||i==2){ return 1; }

        int j = test(i-1)+test(i-2);

        return j;
    }
    /*
    当心i<1
     */
}
