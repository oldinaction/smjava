package cn.aezo.concurrent.ExecutorService.mistake;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 问题：executorService.invokeAll(callables); 执行报错，捕获异常后仍然出现下列问题：
 * 子线程没有结束，Scheduled主线程后面的也尚未执行，但是Scheduled又会重新执行(理论上Scheduled同一段代码不会产生并发)？？？
 * @author smalle
 * @date 2019-07-12 13:34
 */
@SpringBootApplication
@EnableScheduling
public class ScheduledTest {

    public static void main(String[] args) {
        SpringApplication.run(ScheduledTest.class, args);
    }

    @Bean
    public ExecutorService scheduledTestExecutorService() {
        ThreadFactory threadFactory = new ThreadFactoryBuilder().setNameFormat("ScheduledTest-pool-%d").build();
        return new ThreadPoolExecutor(15, 20, 1000, TimeUnit.MILLISECONDS,
                // 修改此处队列大小观察执行情况：当队列较小时，可能子线程也没有结束，scheduled就又会执行
                new LinkedBlockingQueue<Runnable>(1000), threadFactory, new ThreadPoolExecutor.AbortPolicy());
    }


    @Autowired
    ExecutorService executorService;

    @Scheduled(cron = "0/1 * * * * ?")
    public void scheduled() {
        System.out.println("---------------------------------------------------------------------start...");
        long t1 = System.currentTimeMillis();

        List<Map<String, Object>> list = findDbList();

        // test1(list); // 1000434
        test2(list); // 67027

        long t2 = System.currentTimeMillis();
        System.out.println("=====================================================================end..." + (t2 - t1));
    }


    private void test1(List<Map<String, Object>> list) {
        for (Map<String, Object> map : list) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println("1========" + e.getMessage());
            }
        }
    }

    private void test2(List<Map<String, Object>> list) {
        final ConcurrentSkipListSet sets = new ConcurrentSkipListSet();
        Set<Callable<String>> callables = new HashSet<Callable<String>>();

        for (Map<String, Object> map : list) {
            callables.add(new Callable<String>() {
                @Override
                public String call() throws Exception {
                    try {
                        sets.add("" + map.get("index"));

                        // System.out.println(Thread.currentThread().getName());

                        Thread.sleep(1000);
                    } catch (Throwable t) {
                        System.out.println("2========" + t.getMessage());
                    }

                    return null;
                }
            });
        }

        try {
            executorService.invokeAll(callables);
        } catch (InterruptedException e) {
            // 如果队列+最大线程不够，此处会报错
            // 导致子线程没有结束，Scheduled主线程后面的也尚未执行，但是Scheduled又会重新执行(理论上Scheduled同一段代码不会产生并发)？？？
            e.printStackTrace();
        }

        System.out.println("=====sets.size() = " + sets.size());
    }


    private List<Map<String, Object>> findDbList() {
        List<Map<String, Object>> list = new ArrayList<>();

        for (int i = 0; i < 1000; i++) {
            HashMap<String, Object> map = new HashMap<>();
            map.put("index", i);
            map.put("status", 0);
            map.put("name", "name-" + i);
            list.add(map);
        }

        return list;
    }

    private void test0() {
        List<Map<String, Object>> list = findDbList();
        for (int i = 0; i < list.size(); i++) {
            final Map map = list.get(i); // 赋值正常，后面同样不能变更map引用
            System.out.println("map = " + map);
        }

        // final Integer i = 1;
        // i = 2;// 语法错误

        // final Map map = new HashMap();
        // map.put("a", 1); // 运行正常
        // map = new HashMap(); // 语法错误
    }

}
