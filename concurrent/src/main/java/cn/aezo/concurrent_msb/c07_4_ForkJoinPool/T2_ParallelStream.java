package cn.aezo.concurrent_msb.c07_4_ForkJoinPool;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author smalle
 * @date 2020-05-30 20:14
 */
public class T2_ParallelStream {

    public static void main(String[] args) {
        // 1.ParallelStream的执行线程来自于ForkJoinPool#makeCommonPool中的线程或main线程
        testPrintParallelStreamThreadName();

        // 2.ParallelStream是多线程，注意线程安全
        // testThreadSafe();
    }

    public static void testPrintParallelStreamThreadName() {
        List<Integer> lists = Lists.newArrayList();
        for (int i = 0; i < 10000; i++) {
            lists.add(i);
        }

        // 普通的循环
        // [main]
        Set<String> sequenceThreadNameSet = Sets.newHashSet();
        lists.forEach(e -> sequenceThreadNameSet.add(Thread.currentThread().getName()));
        System.out.println(sequenceThreadNameSet);

        // ParallelStream使用了线程名为ForkJoinPool.commonPool-worker-*的线程，而这些线程来自于 ForkJoinPool#makeCommonPool (由此也可说明底层使用了ForkJoinPool)。也可能将main线程作为执行线程
        // [ForkJoinPool.commonPool-worker-1, ForkJoinPool.commonPool-worker-2, main, ForkJoinPool.commonPool-worker-3, ForkJoinPool.commonPool-worker-4]
        Set<String> parallelThreadNameSet = Sets.newHashSet();
        lists.parallelStream().forEach(e -> parallelThreadNameSet.add(Thread.currentThread().getName()));
        System.out.println(parallelThreadNameSet);
    }

    // 此方法执行可能会报错，或者parallelStorage可能产生null
    public static void testThreadSafe() {
        List<Integer> nums = new ArrayList<>();
        for (int i = 0; i <100; i++) {
            nums.add(i);
        }

        // parallelStorage可能产生null，因为在ArrayList中存储数据的过程不是一个线程安全的过程导致的
        List<Integer> parallelStorage = new ArrayList<>();
        nums
            .parallelStream()
            .filter(i->i%2==0)
            .forEach(i->parallelStorage.add(i));

        // 此处为了将null打印在前面。如：null null 0 2 4 6 8 10 12 ...
        parallelStorage
            .stream()
            .sorted((o1, o2) -> {
                if (o1 == null) {
                    return -1;
                } else if (o2 == null) {
                    return 1;
                } else {
                    return o1 > o2 ? 1 : o1.equals(o2) ? 0 : -1;
                }
            })
            .forEach(e -> System.out.print(e + " "));
    }
}
