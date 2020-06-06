package cn.aezo.javase.msb.c05_4_Map;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author smalle
 * @date 2020-06-04 21:15
 */
public class T1_Hashtable_HashMap_ConcurrentHashMap {

    public static void main(String[] args) throws InterruptedException {
        Hashtable map1 = new Hashtable(); // 1000，synchronized
        HashMap map2 = new HashMap<>(); // 如1037，结果可能大于1000
        ConcurrentHashMap map3 = new ConcurrentHashMap(); // 1000，synchronized

        Thread[] threads = new Thread[10];
        for (int i = 0; i < 10; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    map1.put(j, Thread.currentThread().getName() + "-" + j); // synchronized
                    map2.put(j, Thread.currentThread().getName() + "-" + j);
                    map3.put(j, Thread.currentThread().getName() + "-" + j); // synchronized
                }
            });
        }

        for (Thread thread : threads) thread.start();
        for (Thread thread : threads) thread.join();

        System.out.println(map1.size());
        System.out.println(map2.size());
        System.out.println(map3.size());
    }
}
