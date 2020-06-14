package cn.aezo.designpattern.c01_singleton;

import java.util.concurrent.CopyOnWriteArraySet;

/**
 * 懒汉式
 * 1.用到时再初始化
 *
 * @author smalle
 * @date 2020-06-07 21:19
 */
public class Mgr02_1 {
    // private static Mgr02_1 INSTANCE = null;
    private static volatile Mgr02_1 INSTANCE = null; // 加volatile的原因，参考：Mgr02_2_2_Temp

    private Mgr02_1() {}

    /**
     * 缺点：synchronized直接加在方法上，效率可能不太高(如整点秒杀时创建一个单例)
     * @return
     */
    public static synchronized Mgr02_1 getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new Mgr02_1();
        }
        return INSTANCE;
    }


    public static void main(String[] args) throws InterruptedException {
        CopyOnWriteArraySet set = new CopyOnWriteArraySet();

        Thread[] threads = new Thread[100];
        for (int i = 0; i < 100; i++) {
            threads[i] = new Thread(() -> {
                set.add(Mgr02_1.getInstance().hashCode());
            });
        }
        for (Thread thread : threads) thread.start();
        for (Thread thread : threads) thread.join();

        System.out.println("set size: " + set.size());
    }

}
