package cn.aezo.concurrent_msb.c07_2_Callable_Future;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * 实现了RunnableFuture接口，是Runnable和Future的结合
 *
 * @author smalle
 * @date 2020-06-06 17:44
 */
public class T3_FutureTask {

    public static void main(String[] args) {

        FutureTask<Integer> futureTask = new FutureTask<>(() -> {
            System.out.println("run...");
            Thread.sleep(1000);
            return 1;
        });

        new Thread(futureTask).start();

        try {
            System.out.println(futureTask.get()); // 阻塞获取结果
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        System.out.println("end...");
    }

}
