package ch11;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 一个封装类，用于提供多店铺查找价格方法
 */
public class PriceFinder {
    private final List<Shop> shops = Arrays.asList(new Shop("BestPrice"),
            new Shop("LetsSaveBig"),
            new Shop("MyFavoriteShop"),
            new Shop("BuyItAll"),
            new Shop("chenchen"));

    private final Executor executor =
            Executors.newFixedThreadPool(Math.min(shops.size(), 100),
                    new ThreadFactory() {
                        @Override
                        public Thread newThread(Runnable r) {
                            Thread t = new Thread(r);
                            t.setDaemon(true);
                            return t;
                        }
                    }
            );


    // 该方法生成多个写死了店铺名生成的传入产品的价格，以一个列表的方式返回
    public List<String> findPrices(String product) {
         return shops.parallelStream()
                .map(shop -> String.format("%s price is %.2f",
                        shop.getName(), shop.getPrice(product)))
                 .collect(Collectors.toList());
    }

    // 该方法生成多个写死了店铺名生成的传入产品的价格，以列表方式返回
    // 使用CompletableFuture方式，这里注意map分两次写
    public List<String> findPrices02(String product) {
        List<CompletableFuture<String>> priceFutures =
                shops.stream()
                .map(shop -> CompletableFuture.supplyAsync(
                        () -> shop.getPrice(product), executor))
                .collect(Collectors.toList());

        return priceFutures.stream()
                .map(CompletableFuture::join)
                .collect(Collectors.toList());
    }

    // 串行流处理多异步任务
    public List<String> findPricesDis_stream(String product) {
        return shops.stream()
                .map(shop -> shop.getPrice(product))
                .map(Quote::parse)
                .map(Discount::applyDiscount)
                .collect(Collectors.toList());
    }

    // 用CompletableFuture处理多异步任务
    public List<String> findPricesDis_Compl(String product) {
        List<CompletableFuture<String>> priceFutures = shops.stream()
                .map(shop -> CompletableFuture.supplyAsync(
                        () -> shop.getPrice(product), executor))
                .map( x -> x.thenApply(Quote::parse))
                .map(x -> x.thenCompose(quote ->
                CompletableFuture.supplyAsync(
                        () -> Discount.applyDiscount(quote), executor)))
                .collect(Collectors.toList());

        return priceFutures.stream()
                .map(CompletableFuture::join)
                .collect(Collectors.toList());
    }

    // 不采用thenCompose
    public List<String> findTest(String product) {
        Stream<CompletableFuture<String>> cf01 = shops.stream().map(shop -> CompletableFuture.supplyAsync(
                () -> shop.getPrice(product), executor));
        Stream<CompletableFuture<Quote>> cf02 = cf01.map(x ->
                x.thenApply(Quote::parse));

//        Stream<CompletableFuture<String>> cf03 = cf02.map(x -> x.thenCompose(quote ->
//                CompletableFuture.supplyAsync(
//                        () -> Discount.applyDiscount(quote), executor)));
        Stream<CompletableFuture<String>> cf04 = cf02.map(x -> x.thenApply(Discount::applyDiscount));

        List<CompletableFuture<String>> priceFutures02 = cf04.collect(Collectors.toList());

        return priceFutures02.stream().map(CompletableFuture::join)
                .collect(Collectors.toList());
    }

    public Stream<CompletableFuture<String>> findPricesStream(String product) {
        return shops.stream()
                .map(shop -> CompletableFuture.supplyAsync(
                        () -> shop.getPrice(product), executor))
                .map( x -> x.thenApply(Quote::parse))
                .map(x -> x.thenCompose(quote ->
                        CompletableFuture.supplyAsync(
                                () -> Discount.applyDiscount(quote), executor)));
    }

    public void printPricesStream(String product) {
        long start = System.nanoTime();
        CompletableFuture[] futures = findPricesStream(product)
                .map(f -> f.thenAccept(s -> System.out.println(s + " (done in" + ((System.nanoTime() - start) / 1_000_000) + " msecs)")))
                .toArray(size -> new CompletableFuture[size]);
//                .map(f -> f.thenAccept(s -> {
//                    System.out.println(s + " (done in" + ((System.nanoTime() - start) / 1_000_000) + " msecs)");
//                }))
//                .toArray(size -> new CompletableFuture[size]);

        CompletableFuture.allOf(futures).join();

        System.out.println("All shops have now responded in " + ((System.nanoTime() - start) / 1_000_000) + " msecs");

    }


}
