package demo;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * thenAccept在Future计算完成后调用，计算结果会作为参数传入
 */
public class TestThenAccept {
    public static void main(String[] args) {
        CompletableFuture completableFuture = CompletableFuture
                .supplyAsync(DoThing::getString);

        CompletableFuture<Void> c = completableFuture.thenAccept(x ->
                System.out.println(completableFuture.join()));
        try {
            Thread.sleep(10000);
            System.out.println(completableFuture.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }

    public static class DoThing {
        public static String getString() {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return "OK";
        }
    }
}
