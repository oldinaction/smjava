package cn.aezo.concurrent_msb.c04_3_producer_consumer;

import java.util.LinkedList;

/**
 * 题目：有一个2个生成者，10个消费者，且容器最大可以装10个产品，如何完成消费和生产过程
 *
 * 使用synchronized，wait，notifyAll来实现
 * 缺点：在唤醒消费者时会同时唤醒生产者，此时会产生锁竞争；反之亦然
 *
 * @see T2_ReentrantLock_producer_consumer 改进方案使用ReentrantLock，Condition
 *
 * @author smalle
 * @date 2020-05-28 13:12
 */
public class T1_Synchronized_producer_consumer<T> {
    private final LinkedList<T> list = new LinkedList<>(); // 线程不安全的，此时仅为了测试ReentrantLock实现生产者消费者过程
    private final int MAX = 10;

    private synchronized void put(T str) {
        // 此处需要使用while，而不能是if
        while (list.size() == MAX) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        list.add(str);
        this.notify();
    }

    private synchronized T get() {
        T t;
        while (list.size() == 0) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        t = list.removeFirst();
        this.notifyAll();
        return t;
    }

    public static void main(String[] args) {
        T1_Synchronized_producer_consumer obj = new T1_Synchronized_producer_consumer();

        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                for(int j=0; j<5; j++) System.out.println(Thread.currentThread().getName() + "==>" + obj.get());
            }, "c" + i).start();
        }

        for (int i = 0; i < 2; i++) {
            new Thread(() -> {
                for(int j=0; j<25; j++) obj.put(Thread.currentThread().getName());
            }, "p" + i).start();
        }
    }


}
