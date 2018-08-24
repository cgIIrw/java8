package ch05;

import java.util.Arrays;
import java.util.stream.Stream;

// 本实例用来编写创建流的几种方式
public class CreateStreamDemo {

    public static void main(String[] args) {

        // 空流，流元素可以是任何类型，比如下面<>内的Object
        Stream<Object> stream01 = Stream.empty();

        // 数组创建流，主要测试sum函数
        int[] numbers = {2, 3, 4, 5, 6, 7};
        int sum = Arrays.stream(numbers).sum();

        //
        Stream.iterate(0, x -> x + 1)
                .limit(10)
                .forEach(x -> System.out.print(x + " "));
    }
}
