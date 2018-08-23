package ch05;

import java.util.Arrays;
import java.util.List;

/**
 * 这个例子主要展现distinct函数的用法，它确保没有重复
 */
public class DistinctDemo {
    public static void main(String[] args) {
        List<Integer> num = Arrays.asList(1, 2, 1, 3, 3, 2, 4);

        num.stream()
                .filter(x -> x % 2 == 0)
                .distinct()
                .forEach(x -> System.out.println(x));
    }
}
