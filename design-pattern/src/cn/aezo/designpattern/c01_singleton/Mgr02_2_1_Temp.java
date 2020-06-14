package cn.aezo.designpattern.c01_singleton;

import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @author smalle
 * @date 2020-06-07 21:24
 */
public class Mgr02_2_1_Temp {

    private static Mgr02_2_1_Temp INSTANCE = null;

    private Mgr02_2_1_Temp() {}

    public static Mgr02_2_1_Temp getInstance() {
        // 创建INSTANCE后，此值为不空，可排除大部分情况，减少直接上锁的可能
        if(INSTANCE == null) {
            // 存在问题：有可能两个线程同时判断INSTANCE == null都返回true，同时进入到此处，先后得到锁，创建了两个实例，下面main方法中set.size > 1
            synchronized (Mgr02_2_1_Temp.class) {
                try {
                    Thread.sleep(1); // 模拟执行了业务代码，更容易模拟出错误的情况
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                INSTANCE = new Mgr02_2_1_Temp();
            }
        }
        return INSTANCE;
    }


    public static void main(String[] args) throws InterruptedException {
        CopyOnWriteArraySet set = new CopyOnWriteArraySet();

        Thread[] threads = new Thread[100];
        for (int i = 0; i < 100; i++) {
            threads[i] = new Thread(() -> {
                set.add(Mgr02_2_1_Temp.getInstance().hashCode());
            });
        }
        for (Thread thread : threads) thread.start();
        for (Thread thread : threads) thread.join();

        System.out.println("set size: " + set.size());
    }
}
