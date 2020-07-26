package cn.aezo.concurrent_msb.c07_3_ThreadPoolExecutor;

import java.io.IOException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 结果如(有时main只有一个)：
 *
 * pool-1-thread-1
 * pool-1-thread-4
 * pool-1-thread-5
 * pool-1-thread-3
 * pool-1-thread-2
 * main
 * main
 * pool-1-thread-5
 * pool-1-thread-3
 * pool-1-thread-4
 * pool-1-thread-1
 * pool-1-thread-2
 * pool-1-thread-1
 * pool-1-thread-5
 * pool-1-thread-2
 *
 * @author smalle
 * @date 2020-06-06 17:19
 */
public class T1_ThreadPoolExecutor {
    static AtomicInteger count = new AtomicInteger(0);

    public static void main(String[] args) throws IOException {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                // 核心线程数、最大线程数、线程存活时间、存活时间单位
                3, 5, 3, TimeUnit.MINUTES,
                // 等待队列。当核心线程满后，新的任务放入到队列中进行等待
                new ArrayBlockingQueue<>(8),
                // 线程工厂，用来创建执行任务的线程
                Executors.defaultThreadFactory(),
                // 拒绝策略，当线程队列满后，新的任务会创建到最大线程，然后当队列和最大线程达到最大值时，新的人任务将会根据拒绝策略进行响应。此时为将新任务交由调用者执行
                new ThreadPoolExecutor.CallerRunsPolicy()
        );

        for (int i = 0; i < 15; i++) {
            threadPoolExecutor.submit(() -> {
                System.out.println(Thread.currentThread().getName());
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }

        threadPoolExecutor.shutdown();
    }
}
