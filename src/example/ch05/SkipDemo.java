package ch05;

import java.util.Arrays;
import java.util.List;

/**
 * 本例主要是测试.skip()和limit()
 * 《Java 8实战》说limit(n)和skip(n)是互补的，意思应该说的是能获得互斥的子集吧
 */
public class SkipDemo {
    public static void main(String[] args) {
        List<Integer> num = Arrays.asList(1, 2, 3, 4, 5, 6, 7);

        num.stream()
                .skip(2)
                .limit(2)
                .forEach(x -> System.out.println(x));
    }
}
