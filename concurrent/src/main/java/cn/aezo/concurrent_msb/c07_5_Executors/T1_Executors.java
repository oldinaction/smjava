package cn.aezo.concurrent_msb.c07_5_Executors;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author smalle
 * @date 2020-06-06 21:49
 */
public class T1_Executors {

    public static void main(String[] args) {
        // 简单的获取ExecutorService
        ExecutorService executorService1 = Executors.newSingleThreadExecutor();
        ExecutorService executorService2 = Executors.newFixedThreadPool(3);
        ExecutorService executorService3 = Executors.newCachedThreadPool();
        ExecutorService executorService4 = Executors.newScheduledThreadPool(3);
        ExecutorService executorService5 = Executors.newWorkStealingPool(); // 基于ForkJoinPool

        // newScheduledThreadPool
        // scheduledThreadPool();

        // newWorkStealingPool，基于ForkJoinPool
        // workStealingPool();
    }

    public static void scheduledThreadPool() {
        ScheduledExecutorService service = Executors.newScheduledThreadPool(4);
        service.scheduleAtFixedRate(()->{
            try {
                TimeUnit.MILLISECONDS.sleep(new Random().nextInt(1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName());
        }, 0, 500, TimeUnit.MILLISECONDS);
    }

    public static void workStealingPool() {
        ExecutorService service = Executors.newWorkStealingPool();
        System.out.println(Runtime.getRuntime().availableProcessors()); // 系统内核数

        service.execute(new R(1000));
        service.execute(new R(2000));

        //由于产生的是精灵线程（守护线程、后台线程），主线程不阻塞的话，看不到输出
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static class R implements Runnable {
        int time;

        R(int t) {
            this.time = t;
        }

        @Override
        public void run() {

            try {
                TimeUnit.MILLISECONDS.sleep(time);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(time  + " " + Thread.currentThread().getName());
        }
    }

}
