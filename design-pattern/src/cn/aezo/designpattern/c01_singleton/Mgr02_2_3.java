package cn.aezo.designpattern.c01_singleton;

import java.util.concurrent.CopyOnWriteArraySet;

/**
 * 懒汉式增强
 *
 * @author smalle
 * @date 2020-06-07 21:24
 */
public class Mgr02_2_3 {

    // 解决Mgr02_2_2_Temp的问题：增加了volatile
    private static volatile Mgr02_2_3 INSTANCE = null;

    private Mgr02_2_3() {}

    public static Mgr02_2_3 getInstance() {
        if(INSTANCE == null) {
            synchronized (Mgr02_2_3.class) {
                // 双重检查解决Mgr02_2_Temp1的问题
                if(INSTANCE == null) {
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    INSTANCE = new Mgr02_2_3();
                }
            }
        }
        return INSTANCE;
    }


    public static void main(String[] args) throws InterruptedException {
        CopyOnWriteArraySet set = new CopyOnWriteArraySet();

        Thread[] threads = new Thread[100];
        for (int i = 0; i < 100; i++) {
            threads[i] = new Thread(() -> {
                set.add(Mgr02_2_3.getInstance().hashCode());
            });
        }
        for (Thread thread : threads) thread.start();
        for (Thread thread : threads) thread.join();

        System.out.println("set size: " + set.size());
    }
}
