package ch08;

/**
 * 这个例子说明短路中的惰性求值
 */
public class LambdaTestDemo00 {

    public static void main(String[] args) {
        eagerTest(false, false);
    }

    public static boolean isPositive(int x,int y) {
        return x/y >0 ;
    }

    public static void eagerTest(boolean be1, boolean be2) {//方法实参的求值是饥饿的
        System.out.println(be1 && be2); //实参

        // isPsitive(1, 0)并没有执行
        System.out.println(isPositive(-1,1) && isPositive(1,0)); //&&是短路的
    }

}
