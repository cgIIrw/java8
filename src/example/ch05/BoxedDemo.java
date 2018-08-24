package ch05;

/**
 * 书中提到了暗含装箱成本，这个例子就是来研究怎么暗含装箱的
 */

import java.util.Arrays;
import java.util.stream.Stream;

public class BoxedDemo {
    public static void main(String[] args) {

        // arr01是一个字符串数组
        String[] arr01 = {"1", "2", "3", "4"};
        // 在这里个例子中，转换成了Stream<String>类型
        Stream<String> stream01 = Arrays.stream(arr01);

        // arr02是一个int型数组
        int[] arr02 = {1, 2, 3, 4};
        // 此时IDE提示需要一个IntStream类型，而如果使用Stream<Integer>仍然会报错
        // Stream<Integer> stream02 = Arrays.stream(arr02);
        // 也就是说在这里Stream API会提供原始类型流特化

        // 如果不强制转换为Stream<Integer> (实际无法转换，IDE运行时会报错)，那么reduce会重载为int版本，
        Arrays.stream(arr02).reduce(0, Integer::sum);

        // 原题中采用，mapToInt是将Stream<Integer>转换为IntStream，这里一开始就无法转换成
        // Stream<Integer> (IDE运行时会报错)
        Arrays.stream(arr02).map(x -> x);

        // 上一步强制转会会报错，这里应该采用boxed()，它可以将特化流转换为Stream<Integer>

        System.out.println();
    }
}
