package cn.aezo.concurrent_msb.c05_3_Queue;

import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * @author smalle
 * @date 2020-06-04 09:34
 */
public class T7_ConcurrentLinkedQueue {

    public static void main(String[] args) throws InterruptedException {
        ConcurrentLinkedQueue<String> queue = new ConcurrentLinkedQueue<>();

        Thread[] threads = new Thread[10];
        for (int i = 0; i < 10; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    queue.add(Thread.currentThread().getName() + "-" + j); // cas
                }
            });
        }

        for (Thread thread : threads) thread.start();
        for (Thread thread : threads) thread.join();

        System.out.println(queue.size()); // 10000

    }

}
