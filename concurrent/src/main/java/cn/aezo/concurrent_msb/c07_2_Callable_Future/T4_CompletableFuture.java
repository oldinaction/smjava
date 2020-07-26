package cn.aezo.concurrent_msb.c07_2_Callable_Future;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * 结果如：
 *
 * After 308 sleep!
 * After 392 sleep!
 * After 207 sleep!
 * use serial method call! 958
 * After 48 sleep!
 * After 190 sleep!
 * After 498 sleep!
 * use completable future! 514
 * After 317 sleep!
 * price 1.0
 *
 * @author smalle
 * @date 2020-06-06 17:50
 */
public class T4_CompletableFuture {

    public static void main(String[] args) throws IOException {
        test1();

        test2();

        System.in.read();
    }

    private static void test1() {
        long start, end;
        start = System.currentTimeMillis();
        priceOfTM();
        priceOfTB();
        priceOfJD();
        end = System.currentTimeMillis();
        System.out.println("use serial method call! " + (end - start));
    }

    private static void test2() {
        long start, end;
        start = System.currentTimeMillis();

        CompletableFuture<Double> futureTM = CompletableFuture.supplyAsync(T4_CompletableFuture::priceOfTM);
        CompletableFuture<Double> futureTB = CompletableFuture.supplyAsync(T4_CompletableFuture::priceOfTB);
        CompletableFuture<Double> futureJD = CompletableFuture.supplyAsync(T4_CompletableFuture::priceOfJD);

        // 等待所有结果
        CompletableFuture.allOf(futureTM, futureTB, futureJD).join();
        end = System.currentTimeMillis();
        System.out.println("use completable future! " + (end - start));

        // 链式调用
        CompletableFuture.supplyAsync(T4_CompletableFuture::priceOfTM)
                .thenApply(String::valueOf)
                .thenApply(str -> "price " + str)
                .thenAccept(System.out::println);
    }

    private static double priceOfTM() {
        delay();
        return 1.00;
    }

    private static double priceOfTB() {
        delay();
        return 2.00;
    }

    private static double priceOfJD() {
        delay();
        return 3.00;
    }

    private static void delay() {
        int time = new Random().nextInt(500);
        try {
            TimeUnit.MILLISECONDS.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.printf("After %s sleep!\n", time);
    }
}
