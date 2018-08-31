package ch11;

import java.util.List;
import java.util.function.Supplier;

/**
 * 执行多店铺查找价格方法的主类
 */
public class BestPriceFinderMain {
    static PriceFinder priceFinder = new PriceFinder();

    public static void main(String[] args) {
        execute("parallelStream", () -> priceFinder.findTest("myPhone27S"));
    }

    //
    private static void execute(String msg, Supplier<List<String>> s) {
        long start = System.nanoTime();

        // 第一句打印语句，用于返回列表
        System.out.println(s.get());
        long duration = (System.nanoTime() - start) / 1_000_000;

        // 用于打印经过时间
        System.out.println(msg + " done in " + duration + " msecs");
    }
}
