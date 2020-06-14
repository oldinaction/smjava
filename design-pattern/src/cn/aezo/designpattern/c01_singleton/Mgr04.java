package cn.aezo.designpattern.c01_singleton;

import java.util.concurrent.CopyOnWriteArraySet;

/**
 * Created by smalle on 2020-06-07 22:04.
 */
public enum Mgr04 {

    INSTANCE;

    public void test() {
        System.out.println("test...");
    }

    public static void main(String[] args) throws InterruptedException {
        CopyOnWriteArraySet set = new CopyOnWriteArraySet();

        Thread[] threads = new Thread[100];
        for (int i = 0; i < 100; i++) {
            threads[i] = new Thread(() -> {
                set.add(Mgr04.INSTANCE.hashCode());
            });
        }
        for (Thread thread : threads) thread.start();
        for (Thread thread : threads) thread.join();

        System.out.println("set size: " + set.size());
        Mgr04.INSTANCE.test();
    }
}
