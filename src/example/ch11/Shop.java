package ch11;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

/**
 * 代表各个店铺的Shop
 */
public class Shop {

    private final String name;
    private final Random random ;

    // 通过传递店铺名，设置name和用作随机算子
    public Shop(String name) {
        this.name = name;
        this.random = new Random(name.charAt(0) * name.charAt(1) * name.charAt(2));
    }

    // 获得价格，传入产品名，返回计算的价格
    public String getPrice(String product) {
        double price = calculatePrice(product);
        Discount.Code code = Discount.Code.values()[random
                .nextInt(Discount.Code.values().length)];
        return String.format("%s:%.2f:%s", name, price, code);
    }

    // 模拟的延迟动作
    public static void delay() {
        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // 模拟的计算价格方法，包括延迟动作以及返回一个根据算子随机产生的一个模拟价格
    // 传入产品名，由于每个店名不一样，所以随机算子不一样，于是模拟出不同价格
    private double calculatePrice(String product) {
        delay();
        return random.nextDouble() * product.charAt(0) + product.charAt(1);
    }

    // 获得异步价格的方法，比对常规获得价格的方法getPrice
    public Future<Double> getPriceAsync(String product) {
        CompletableFuture<Double> futurePrice = new CompletableFuture<>();
        new Thread( () -> {
            double price = calculatePrice(product);
            futurePrice.complete(price);
        }).start();
        return futurePrice;
    }

    public Future<Double> getPriceAsync02(String product) {
        return CompletableFuture.supplyAsync(()-> calculatePrice(product));
    }

    // 返回店铺名
    public String getName() {
        return name;
    }
}
