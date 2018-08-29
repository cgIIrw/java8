package ch08;

/**
 * 尾递归尾调用
 */
public class TailCallDemo {

    public static void main(String[] args) {

        int n = 5;
        int num = add01(n);
        System.out.println(num);

        int num02 = add02(n);

        System.out.println(num02);
    }

    static int add01(int x) {

        System.out.println("add01: " + x);
        return x == 1 ? 1 : 2 * add01(x - 1);
    }

    private static int add0(int acc, int x) {
        System.out.println("add02: " + x);
        return x == 1 ? acc : add0(acc * 2, x - 1);
    }

    static int add02(int x) {

        return add0(1, x);
    }
}
