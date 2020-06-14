package cn.aezo.designpattern.c01_singleton;

import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @author smalle
 * @date 2020-06-07 21:48
 */
public class Mgr03 {

    // 内部类只有在用到时才会加载到内存
    private static class Mgr03Instance {
        private static final Mgr03 INSTANCE = new Mgr03();
    }

    public static Mgr03 getInstance() {
        return Mgr03Instance.INSTANCE;
    }


    public static void main(String[] args) throws InterruptedException {
        CopyOnWriteArraySet set = new CopyOnWriteArraySet();

        Thread[] threads = new Thread[100];
        for (int i = 0; i < 100; i++) {
            threads[i] = new Thread(() -> {
                set.add(Mgr03.getInstance().hashCode());
            });
        }
        for (Thread thread : threads) thread.start();
        for (Thread thread : threads) thread.join();

        System.out.println("set size: " + set.size());
    }

}
