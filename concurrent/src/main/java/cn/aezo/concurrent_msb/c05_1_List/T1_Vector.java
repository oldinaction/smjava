package cn.aezo.concurrent_msb.c05_1_List;

import java.util.Vector;

/**
 * Vector为线程安全
 *
 * @author smalle
 * @date 2020-06-03 21:39
 */
public class T1_Vector {

    public static void main(String[] args) throws InterruptedException {
        Vector<String> vector = new Vector<>();

        Thread[] threads = new Thread[10];
        for (int i = 0; i < 10; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    vector.add(Thread.currentThread().getName() + "-" + j); // synchronized
                }
            });
        }

        for (Thread thread : threads) thread.start();
        for (Thread thread : threads) thread.join();

        System.out.println(vector.size()); // 10000
    }
}
