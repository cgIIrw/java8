package ch05;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 *  本例主要用于研究流水线的返回值类型
 */
public class FlatMapDemo {

    public static void main(String[] args) {

        String[] words = {"Goodbye", "World"};

        // 这行代码打印出Goodbye World，说明此时流元素是一个String类型
        Arrays.stream(words).forEach(x -> System.out.print(x + " "));

        List<String> list = new ArrayList<>();
        list.add("Hello");
        list.add("World");

        // 说明此时流元素是Stream<String>类型
        Stream<String> s0 = list.stream();
        Stream<String[]> s1 = s0.map(x -> x.split(""));

        // 下面代码是一系列流水线操作，旨在说明流中流元素的类型
        Stream<String> stream01 = list.stream();
        Stream<String[]> stream02 = stream01.map(x -> x.split(""));
        Stream<Stream<String>> stream03 = stream02.map(x -> Arrays.stream(x));
        Stream<Object[]> stream04 = stream03.map(x -> (x.toArray()));
        Stream<Object> stream05 = stream04.flatMap(x -> Arrays.stream(x));
        System.out.println(stream05.collect(Collectors.toList()));
    }
}
