package cn.aezo.javase.msb.c05_1_List;

import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author smalle
 * @date 2020-06-03 21:49
 */
public class T2_CopyOnWriteList {

    public static void main(String[] args) throws InterruptedException {
        CopyOnWriteArrayList<String> copyOnWriteArrayList = new CopyOnWriteArrayList<>();

        Thread[] threads = new Thread[10];
        for (int i = 0; i < 10; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    copyOnWriteArrayList.add(Thread.currentThread().getName() + "-" + j); // synchronized
                }
            });
        }

        for (Thread thread : threads) thread.start();
        for (Thread thread : threads) thread.join();

        System.out.println(copyOnWriteArrayList.size()); // 10000
        System.out.println(copyOnWriteArrayList.get(0));
    }

}
