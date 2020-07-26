package cn.aezo.jvm.c05_gc;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 1.模拟风控系统：从数据库中读取信用数据，套用模型，并把结果进行记录和传输
 *
 * 2.此案例会出现CPU飙高(频繁FGC导致，但是每次回收很少内存)。可使用 -Xmn20M -Xms50M -Xmx50M -XX:+PrintGCDetails -XX:PrintGCTimeStamps 观察(需要一段时间)如下信息：
 *
 * 2.993: [GC (Allocation Failure) [PSYoungGen: 15360K->1688K(17920K)] 15360K->1696K(48640K), 0.0069461 secs] [Times: user=0.00 sys=0.00, real=0.01 secs]
 * 25.479: [GC (Allocation Failure) [PSYoungGen: 17048K->2545K(17920K)] 17056K->6265K(48640K), 0.0159527 secs] [Times: user=0.09 sys=0.03, real=0.02 secs]
 * 57.072: [GC (Allocation Failure) [PSYoungGen: 17905K->2540K(17920K)] 21625K->12880K(48640K), 0.0240804 secs] [Times: user=0.11 sys=0.00, real=0.02 secs]
 * 90.826: [GC (Allocation Failure) [PSYoungGen: 17900K->2534K(17920K)] 28240K->19834K(48640K), 0.0266654 secs] [Times: user=0.14 sys=0.01, real=0.03 secs]
 * 127.809: [GC (Allocation Failure) [PSYoungGen: 17894K->2557K(17920K)] 35194K->27593K(48640K), 0.0308223 secs] [Times: user=0.22 sys=0.03, real=0.03 secs]
 * 127.840: [Full GC (Ergonomics) [PSYoungGen: 2557K->0K(17920K)] [ParOldGen: 25036K->27221K(30720K)] 27593K->27221K(48640K), [Metaspace: 4834K->4828K(1056768K)], 0.3081102 secs] [Times: user=2.06 sys=0.03, real=0.31 secs]
 * 166.005: [Full GC (Ergonomics) [PSYoungGen: 15360K->4520K(17920K)] [ParOldGen: 27221K->30277K(30720K)] 42581K->34797K(48640K), [Metaspace: 4828K->4828K(1056768K)], 0.1802677 secs] [Times: user=1.08 sys=0.05, real=0.18 secs]
 * 189.781: [Full GC (Ergonomics) [PSYoungGen: 15360K->9244K(17920K)] [ParOldGen: 30277K->30608K(30720K)] 45637K->39853K(48640K), [Metaspace: 4828K->4828K(1056768K)], 0.2209186 secs] [Times: user=1.33 sys=0.00, real=0.22 secs]
 * 204.314: [Full GC (Ergonomics) [PSYoungGen: 15360K->12072K(17920K)] [ParOldGen: 30608K->30608K(30720K)] 45968K->42680K(48640K), [Metaspace: 4828K->4828K(1056768K)], 0.2040221 secs] [Times: user=1.41 sys=0.03, real=0.20 secs]
 * 212.329: [Full GC (Ergonomics) [PSYoungGen: 15360K->13615K(17920K)] [ParOldGen: 30608K->30608K(30720K)] 45968K->44224K(48640K), [Metaspace: 4828K->4828K(1056768K)], 0.2171191 secs] [Times: user=1.30 sys=0.00, real=0.22 secs]
 * ...
 * 228.023: [Full GC (Ergonomics) [PSYoungGen: 15360K->15302K(17920K)] [ParOldGen: 30608K->30608K(30720K)] 45968K->45910K(48640K), [Metaspace: 4830K->4830K(1056768K)], 0.1653492 secs] [Times: user=1.17 sys=0.02, real=0.17 secs]
 * Exception in thread "pool-1-thread-31"
 * java.lang.OutOfMemoryError: GC overhead limit exceeded
 * ...
 *
 * @author smalle
 * @date 2020-07-05 22:49
 */
public class T03_FullGC_Problem01 {
    private static ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(50,
            new ThreadPoolExecutor.DiscardOldestPolicy());

    public static void main(String[] args) throws Exception {
        executor.setMaximumPoolSize(50);

        for (;;) {
            modelFit();
            Thread.sleep(100);
        }
    }

    private static void modelFit(){
        List<CardInfo> taskList = getAllCardInfo();
        taskList.forEach(info -> {
            // do something
            executor.scheduleWithFixedDelay(() -> {
                //do sth with info
                info.m();
            }, 2, 3, TimeUnit.SECONDS);
        });
    }

    private static List<CardInfo> getAllCardInfo() {
        List<CardInfo> taskList = new ArrayList<>();

        for (int i = 0; i < 100; i++) {
            CardInfo ci = new CardInfo();
            taskList.add(ci);
        }

        return taskList;
    }

    private static class CardInfo {
        BigDecimal price = new BigDecimal(0.0);
        String name = "张三";
        int age = 5;
        Date birthdate = new Date();

        public void m() {}
    }
}
