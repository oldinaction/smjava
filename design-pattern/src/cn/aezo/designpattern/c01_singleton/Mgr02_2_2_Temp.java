package cn.aezo.designpattern.c01_singleton;

import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @author smalle
 * @date 2020-06-07 21:24
 */
public class Mgr02_2_2_Temp {

    private static Mgr02_2_2_Temp INSTANCE = null;
    // private static volatile Mgr02_2_2_Temp INSTANCE = null;

    private Mgr02_2_2_Temp() {}

    public static Mgr02_2_2_Temp getInstance() {
        if(INSTANCE == null) {
            synchronized (Mgr02_2_2_Temp.class) {
                // 双重检查解决 Mgr02_2_1_Temp 的问题
                if(INSTANCE == null) {
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    /*
                     * Object o = new Object(); 可分为4步(此处同理)
                     * 1.new #11 <java/lang/Object>：申请内存(并设置默认值，如设置此o对象的某属性为int a = 0)
                     * 2.dup
                     * 3.invokespecial #1 <java/lang/Object.<init>>：实例化对象(设置属性的初始值，a = 1)
                     * 4.astore_1：将此对象的引用赋值给变量o
                     *
                     * 如果INSTANCE不加volatile则可能出现指令重排，可能出现1-2-4-3的执行顺序(就是将没有初始化完全的对象引用提前赋值给了变量)
                     * 如果第一个线程执行按照此方式执行到第4(还未执行3)，第二个线程判断发现INSTANCE不为空(已经被赋值了引用地址)
                     * 则第二个线程可能会使用第一个线程创建的对象，此时可能使用到对象中的一些未初始化好的属性产生意想不到的结果。很难测试出此问题
                     */
                    INSTANCE = new Mgr02_2_2_Temp();
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
                set.add(Mgr02_2_2_Temp.getInstance().hashCode());
            });
        }
        for (Thread thread : threads) thread.start();
        for (Thread thread : threads) thread.join();

        System.out.println("set size: " + set.size());
    }
}
