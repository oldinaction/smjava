package cn.aezo.concurrent_msb.c03_1_Atomic;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author smalle
 * @date 2020-05-27 23:21
 */
public class T1_AtomicInteger {
    private AtomicInteger count = new AtomicInteger(0);

    public static void main(String[] args) throws InterruptedException {
        T1_AtomicInteger obj = new T1_AtomicInteger();

        Thread[] threads = new Thread[1000];
        for (int i = 0; i < 1000; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < 10000; j++) {
                    obj.count.incrementAndGet();
                }
            });
        }

        for (Thread thread : threads) {
            thread.start();
        }
        for (Thread thread : threads) {
            thread.join();
        }

        System.out.println("obj.count.get() = " + obj.count.get());
    }

}
