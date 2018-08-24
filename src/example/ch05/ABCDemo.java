package ch05;

import java.util.stream.IntStream;

/**
 * 数值流的应用：勾股数
 */
public class ABCDemo {

    public static void main(String[] args) {
        IntStream stream01 = IntStream.rangeClosed(1, 100);

        // 思考这里为什么需要boxed()
        stream01.boxed().flatMap(a -> IntStream.rangeClosed(a, 100)
                .filter(b -> Math.sqrt(a * a + b * b) % 1 == 0)
                .mapToObj(b ->
                new int[]{a, b, (int)Math.sqrt(a * a + b * b)}));
    }
}
