package ch08;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.function.Supplier;

// 环绕执行
public class LambdaTestDemo03 {
    public static void main(String[] args) throws IOException {
        String oneLine = processFile(x -> x.readLine() + x.readLine());
    }

    public static String processFile(BufferedReaderProcessor b) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader("data.txt"))) {
            return b.process(br);
        }
    }

    @FunctionalInterface
    public static interface BufferedReaderProcessor {
        String process(BufferedReader b) throws IOException;
    }
}
