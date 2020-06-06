package cn.aezo.javase.msb.c03_1_Atomic;

import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAdder;

/**
 * 当线程较多时：LongAdder > AtomicLong > synchronized
 * @author smalle
 * @date 2020-05-27 09:09
 */
public class T2_LongAdderVsAtomicVsSync {
    private LongAdder count1 = new LongAdder();
    private AtomicLong count2 = new AtomicLong(0L);
    private long count3 = 0L;
    private Object o = new Object();

    private void longAdder() {
        for (int i = 0; i < 100000; i++) {
            count1.add(1);
        }
    }

    private void atomicLong() {
        for (int i = 0; i < 100000; i++) {
            count2.incrementAndGet();
        }
    }

    private void sync() {
        for (int i = 0; i < 100000; i++) {
            synchronized (o) {
                count3++;
            }
        }
    }

    /**
     * sq
     * 1.相比第一种sync效率高是因为减少了锁的竞争次数，第一次相当于1000个线程会进行10w次锁的竞争
     * 2.此方法对于AtomicLong/LongAdder不具可比性，因为后者都是在循环里面加锁的，因此第一种sync方法才更能证明三者之间的效率
     */
    private synchronized void sync2() {
        for (int i = 0; i < 100000; i++) {
            count3++;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        T2_LongAdderVsAtomicVsSync vs = new T2_LongAdderVsAtomicVsSync();
        Thread[] threads = new Thread[1000];

        // longAdder
        for (int i = 0; i < 1000; i++) {
            threads[i] = new Thread(() -> {
                vs.longAdder();
            });
        }
        long t1 = System.currentTimeMillis();
        for(Thread t : threads) t.start();
        for(Thread t : threads) t.join();
        long t2 = System.currentTimeMillis();
        System.out.println("longAdder   , t2-t1 = " + (t2-t1) + ", count1=" + vs.count1);

        // atomicLong
        for (int i = 0; i < 1000; i++) {
            threads[i] = new Thread(() -> {
                vs.atomicLong();
            });
        }
        t1 = System.currentTimeMillis();
        for(Thread t : threads) t.start(); // 启动所有线程
        for(Thread t : threads) t.join(); // 相当于等待所有t线程执行结束再继续往下执行
        t2 = System.currentTimeMillis();
        System.out.println("atomicLong  , t2-t1 = " + (t2-t1) + ", count2=" + vs.count2);

        // synchronized
        for (int i = 0; i < 1000; i++) {
            threads[i] = new Thread(() -> {
                vs.sync();
            });
        }
        t1 = System.currentTimeMillis();
        for(Thread t : threads) t.start();
        for(Thread t : threads) t.join();
        t2 = System.currentTimeMillis();
        System.out.println("synchronized, t2-t1 = " + (t2-t1) + ", count3=" + vs.count3);

        // synchronized2
        vs.count3 = 0L;
        for (int i = 0; i < 1000; i++) {
            threads[i] = new Thread(() -> {
                vs.sync2();
            });
        }
        t1 = System.currentTimeMillis();
        for(Thread t : threads) t.start();
        for(Thread t : threads) t.join();
        t2 = System.currentTimeMillis();
        System.out.println("synchronized2, t2-t1 = " + (t2-t1) + ", count3=" + vs.count3);
    }
}
