package cn.aezo.concurrent_msb.c05_2_Set;

import java.util.Comparator;
import java.util.HashSet;
import java.util.Random;
import java.util.TreeSet;

/**
 * 1.TreeSet 为有序Set，默认找元素大小排序，可定义比较器
 * 2.TreeSet 中的元素必须实现Comparable接口并重写compareTo()方法
 * 3.线程不安全
 *
 * @author smalle
 * @date 2020-06-03 21:59
 */
public class T1_SortedSet {

    public static void main(String[] args) throws InterruptedException {
        test1();

        // test2();
    }

    /**
     * 基本用法
     */
    private static void test1() {
        HashSet<String> hashSet = new HashSet<>();
        TreeSet<String> treeSet = new TreeSet<>();
        TreeSet<String> treeSet2 = new TreeSet<>(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o2.compareTo(o1);
            }
        });

        Random r = new Random();

        for (int j = 0; j < 10; j++) {
            int i = r.nextInt(100);
            hashSet.add(i + "");
            treeSet.add(i + "");
            treeSet2.add(i + "");
        }

        System.out.println(hashSet);
        System.out.println(treeSet);
        System.out.println(treeSet2);
    }

    /**
     * 线程不安全
     * @throws InterruptedException
     */
    private static void test2() throws InterruptedException {
        TreeSet<String> treeSet = new TreeSet<>();

        Thread[] threads = new Thread[10];
        for (int i = 0; i < 10; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    treeSet.add(Thread.currentThread().getName() + "-" + j); // 线程不安全，可能会报错NullPointerException
                }
            });
        }

        for (Thread thread : threads) thread.start();
        for (Thread thread : threads) thread.join();

        System.out.println(treeSet.size());
    }

}
