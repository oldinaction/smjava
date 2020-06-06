package cn.aezo.javase.msb.c03_2_ReentrantLock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author smalle
 * @date 2020-05-27 23:25
 */
public class T1_ReentrantLock {

    private static int count = 0;

    public static void main(String[] args) throws InterruptedException {
        Lock lock = new ReentrantLock();
        Thread[] threads = new Thread[1000];
        for (int i = 0; i < 1000; i++) {
            threads[i] = new Thread(() -> {
                try {
                    lock.lock(); //synchronized(this)
                    for (int j = 0; j < 10000; j++) {
                        count++;
                    }
                } finally {
                    lock.unlock();
                }
            });
        }

        for (Thread thread : threads) {
            thread.start();
        }
        for (Thread thread : threads) {
            thread.join();
        }

        System.out.println("count = " + count);
    }

}
