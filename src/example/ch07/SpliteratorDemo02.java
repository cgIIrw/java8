package ch07;

import java.util.stream.IntStream;
import java.util.stream.Stream;

import static ch07.SpliteratorDemo01.SENTENCE;

/**
 *
 */
public class SpliteratorDemo02 {
    Stream<Character> stream = IntStream.range(0, SENTENCE.length()).mapToObj(x -> SENTENCE.charAt(x));

    public static void main(String[] args) {
        // todo
    }

}
