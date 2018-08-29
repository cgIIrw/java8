package ch11;

import java.util.List;
import java.util.function.Supplier;

public class BestPriceFinderMain {
    static PriceFinder priceFinder = new PriceFinder();

    public static void main(String[] args) {
        execute("StreamParallel", () -> priceFinder.findPrices("myPhone27S"));
    }

    private static void execute(String msg, Supplier<List<String>> s) {
        long start = System.nanoTime();
        System.out.println(s.get());
        long duration = (System.nanoTime() - start) / 1_000_000;
        System.out.println(msg + " done in " + duration + " msecs");
    }
}
