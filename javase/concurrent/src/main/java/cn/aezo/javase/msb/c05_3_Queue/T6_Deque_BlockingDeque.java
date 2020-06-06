package cn.aezo.javase.msb.c05_3_Queue;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * @author smalle
 * @date 2020-06-04 09:30
 */
public class T6_Deque_BlockingDeque {

    public static void main(String[] args) {
        BlockingDeque<Integer> blockingDeque = new LinkedBlockingDeque<>();

        Thread[] threads = new Thread[10];
        for (int i = 0; i < 10; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    blockingDeque.add(j);
                }
            });
        }

        for (Thread thread : threads) thread.start();
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println(blockingDeque.size()); // 10000
    }
}
