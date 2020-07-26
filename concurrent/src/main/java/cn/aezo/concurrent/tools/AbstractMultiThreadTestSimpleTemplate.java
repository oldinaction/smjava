package cn.aezo.concurrent.tools;

import java.text.NumberFormat;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 基于 ExecutorService 实现多线程测试模板(推荐)
 * @author smalle
 * @date 2019-07-23 13:58
 */
public abstract class AbstractMultiThreadTestSimpleTemplate {
    // 测试案例=========================================================================================
    static class DemoTest extends AbstractMultiThreadTestSimpleTemplate {
        public static void main(String[] args) {
            // 总共测试执行10000遍，100个并发
            new DemoTest().run(10000, 100);
        }

        @Override
        public void beforeExec() {}

        @Override
        public void exec() {
            System.out.println(Thread.currentThread().getName() + "测试内容...");
        }

        @Override
        public void afterExec() {}
    }

    // 测试模板=========================================================================================
    // 总访问量是totalNum，并发量是threadNum
    private static int totalNum = 10000;
    private static int threadNum = 100;

    private static int count = 0;
    private float sumExecTime = 0;
    private long firstExecTime = Long.MAX_VALUE;
    private long lastDoneTime = Long.MIN_VALUE;

    public abstract void beforeExec();
    public abstract void exec();
    public abstract void afterExec();

    public void run(int totalNum, int threadNum) {
        AbstractMultiThreadTestSimpleTemplate.totalNum = totalNum;
        AbstractMultiThreadTestSimpleTemplate.threadNum = threadNum;
        this.run();
    }

    public void run() {
        beforeExec();

        final ConcurrentHashMap<Integer, ThreadRecord> records = new ConcurrentHashMap<Integer, ThreadRecord>();

        // 建立ExecutorService线程池，threadNum个线程可以同时访问
        ExecutorService es = Executors.newFixedThreadPool(threadNum);
        final CountDownLatch doneSignal = new CountDownLatch(totalNum);

        for (int i = 0; i < totalNum; i++) {
            Runnable run = new Runnable() {
                public void run() {
                    try {
                        int index = ++count;
                        long systemCurrentTimeMillis = System.currentTimeMillis();

                        exec();

                        records.put(index, new ThreadRecord(systemCurrentTimeMillis, System.currentTimeMillis()));
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        // 每调用一次countDown()方法，计数器减1
                        doneSignal.countDown();
                    }
                }
            };
            es.execute(run);
        }

        try {
            // 计数器大于0时，await()方法会阻塞程序继续执行
            doneSignal.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 获取每个线程的开始时间和结束时间
        for (int i : records.keySet()) {
            ThreadRecord r = records.get(i);
            sumExecTime += ((double) (r.endTime - r.startTime)) / 1000;

            if (r.startTime < firstExecTime) {
                firstExecTime = r.startTime;
            }
            if (r.endTime > lastDoneTime) {
                this.lastDoneTime = r.endTime;
            }
        }

        float avgExecTime = this.sumExecTime / records.size();
        float totalExecTime = ((float) (this.lastDoneTime - this.firstExecTime)) / 1000;
        NumberFormat nf = NumberFormat.getNumberInstance();
        nf.setMaximumFractionDigits(4);

        // 需要关闭，否则JVM不会退出
        es.shutdown();

        System.out.println("======================================================");
        System.out.println("线程数量:\t\t" + threadNum);
        System.out.println("客户端数量:\t" + totalNum);
        System.out.println("平均执行时间:\t" + nf.format(avgExecTime) + "秒");
        System.out.println("总执行时间:\t" + nf.format(totalExecTime) + "秒");
        System.out.println("吞吐量:\t\t" + nf.format(totalNum / totalExecTime) + "次每秒");

        afterExec();
    }

    class ThreadRecord {
        long startTime;
        long endTime;

        ThreadRecord(long st, long et) {
            this.startTime = st;
            this.endTime = et;
        }

    }
}
