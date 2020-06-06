package cn.aezo.javase.msb.c04_3_producer_consumer;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 题目：有一个2个生成者，10个消费者，且容器最大可以装10个产品，如何完成消费和生产过程
 *
 * @see T1_Synchronized_producer_consumer
 *
 * @author smalle
 * @date 2020-05-27 23:25
 */
public class T2_ReentrantLock_producer_consumer<T> {
    private final LinkedList<T> list = new LinkedList<>(); // 线程不安全的，此时仅为了测试ReentrantLock实现生产者消费者过程
    private final int MAX = 10;

    private ReentrantLock lock = new ReentrantLock();
    private Condition producer = lock.newCondition();
    private Condition consumer = lock.newCondition();

    private void put(T str) {
        try {
            lock.lock();
            // 此处需要使用while，而不能是if：可能在生产者醒来时，容器中的数量被其他线程线程放满。因此醒来，重新获得锁执行时，while会再次判断容器是否放满
            while (list.size() == MAX) {
                try {
                    producer.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            list.add(str);
            consumer.signalAll(); // 只唤醒消费者队列中线程
        } finally {
            lock.unlock();
        }
    }

    private T get() {
        T t;
        try {
            lock.lock();
            while (list.size() == 0) {
                try {
                    consumer.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            t = list.removeFirst();
            producer.signalAll();
        } finally {
            lock.unlock();
        }
        return t;
    }

    public static void main(String[] args) {
        T2_ReentrantLock_producer_consumer obj = new T2_ReentrantLock_producer_consumer();

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
