package ch05;

import java.util.Arrays;

public class ReduceDemo {

    public static void main(String[] args) {
        String[] s = {"Hello", "World"};

        int count = Arrays.stream(s).map(x -> 1)
                .reduce(0, (a, b) -> a + b);

        System.out.println(count);
    }
}
