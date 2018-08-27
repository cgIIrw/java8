package ch08;


/**
 * 有条件执行
 */
public class LambdaTestDemo04 {

    public static void main(String[] args) {

        int num01 = getNumber01(x -> {
            System.out.println("Lambda");
            return x + x;
        }, 3);

        System.out.println("getNumber01: " + num01);

        int num02 = getNumber02(getNumber03(1), 3);

        System.out.println("getNumber02: " + num02);

    }


    public static int getNumber01(Func<Integer> func, int x) {
        if (x < 3)
            return func.get(x);
        return -1;
    }

    public static int getNumber02(int t, int x) {
        if (x < 3)
            return t + x;
        return -2;
    }

    public static int getNumber03(int x) {
        System.out.println("getNumber03");
        return x + x;
    }

    @FunctionalInterface
    public static interface Func<Integer> {
        int get(int x);
    }
}
