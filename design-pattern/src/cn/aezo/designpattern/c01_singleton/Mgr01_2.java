package cn.aezo.designpattern.c01_singleton;

import java.util.concurrent.CopyOnWriteArraySet;

/**
 * 饿汉式
 *
 * @author smalle
 * @date 2020-06-07 17:38
 */
public class Mgr01_2 {

    private static final Mgr01_2 INSTANCE; // final修饰的属性，要么立即赋值，要么再static中进行赋值

    static {
        INSTANCE = new Mgr01_2();
    }

    private Mgr01_2() {}

    public static Mgr01_2 getInstance() {
        return INSTANCE;
    }


    public static void main(String[] args) throws InterruptedException {
        CopyOnWriteArraySet set = new CopyOnWriteArraySet();

        Thread[] threads = new Thread[100];
        for (int i = 0; i < 100; i++) {
            threads[i] = new Thread(() -> {
                set.add(Mgr01_2.getInstance().hashCode());
            });
        }
        for (Thread thread : threads) thread.start();
        for (Thread thread : threads) thread.join();

        System.out.println("set size: " + set.size());
    }
}
