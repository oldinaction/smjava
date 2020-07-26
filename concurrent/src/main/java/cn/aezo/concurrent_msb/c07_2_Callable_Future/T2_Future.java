package cn.aezo.concurrent_msb.c07_2_Callable_Future;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 结果：
 *
 * run...
 * hello...
 * true
 *
 * @author smalle
 * @date 2020-06-06 17:38
 */
public class T2_Future {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        Future<String> future = executorService.submit(() -> {
            System.out.println("run...");
            Thread.sleep(1000);
            return "hello...";
        });

        try {
            System.out.println(future.get()); // 阻塞获取结果
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        System.out.println(future.isDone());
        executorService.shutdown();
    }
}
