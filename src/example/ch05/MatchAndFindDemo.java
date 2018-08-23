package ch05;

import java.util.ArrayList;
import java.util.List;

/**
 * 这个实例主要用来练习流的查找和匹配
 */
public class MatchAndFindDemo {

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("Hello");
        list.add("World");

        // 这个例子要注意的是anyMatch和forEach不一样，只会运行一次
        if (list.stream().anyMatch(x -> x.substring(3, 4).equals("l"))) {
            System.out.println("--------");
        }

        System.out.println(list.stream()
                .filter(x -> x.substring(4, 4).equals("l"))
                .findAny());
    }
}
