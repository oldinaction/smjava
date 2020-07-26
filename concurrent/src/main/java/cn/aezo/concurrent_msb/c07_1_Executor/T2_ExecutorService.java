package cn.aezo.concurrent_msb.c07_1_Executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author smalle
 * @date 2020-06-06 21:15
 */
public class T2_ExecutorService {
    public static void main(String[] args) throws InterruptedException {

        ExecutorService executorService = Executors.newFixedThreadPool(1);
        executorService.submit(() -> {
            for (int i = 0; i < 10; i++) {
                System.out.println(i);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread.sleep(1000);

        executorService.shutdown();
        // executorService.shutdownNow();
    }
}
