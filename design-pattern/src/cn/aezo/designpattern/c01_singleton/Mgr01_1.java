package cn.aezo.designpattern.c01_singleton;

import java.util.concurrent.CopyOnWriteArraySet;

/**
 * 饿汉式
 * 1.类加载到内存就实例化一个单例，JVM保证线程安全
 * 2.唯一缺点：不管用到与否，类装载时就完成实例化。见懒汉式
 * 3.简单实用，推荐使用
 *
 * @author smalle
 * @date 2020-06-07 17:38
 */
public class Mgr01_1 {

    private static final Mgr01_1 INSTANCE = new Mgr01_1();

    private Mgr01_1() {}

    public static Mgr01_1 getInstance() {
        return INSTANCE;
    }


    public static void main(String[] args) throws InterruptedException {
        CopyOnWriteArraySet set = new CopyOnWriteArraySet();

        Thread[] threads = new Thread[100];
        for (int i = 0; i < 100; i++) {
            threads[i] = new Thread(() -> {
                set.add(Mgr01_1.getInstance().hashCode());
            });
        }
        for (Thread thread : threads) thread.start();
        for (Thread thread : threads) thread.join();

        System.out.println("set size: " + set.size());
    }
}
