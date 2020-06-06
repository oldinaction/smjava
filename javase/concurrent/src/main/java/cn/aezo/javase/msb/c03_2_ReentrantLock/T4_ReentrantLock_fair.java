package cn.aezo.javase.msb.c03_2_ReentrantLock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * ReentrantLock可以指定为公平锁
 *
 * 结果：
 * Thread-0获得锁
 * Thread-1获得锁
 * ...
 * 交替输出
 * ...
 *
 * @author smalle
 * @date 2020-05-28 12:41
 */
public class T4_ReentrantLock_fair implements Runnable {
    private static ReentrantLock lock = new ReentrantLock(true); // 参数为true表示为公平锁
    // private static ReentrantLock lock = new ReentrantLock(); // 非交替输出，可多执行几次观察输出

    public void run() {
        for(int i=0; i<100; i++) {
            try{
                lock.lock();
                System.out.println(Thread.currentThread().getName()+"获得锁");
            } finally {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) {
        T4_ReentrantLock_fair rl = new T4_ReentrantLock_fair();
        Thread th1 = new Thread(rl);
        Thread th2 = new Thread(rl);
        th1.start();
        th2.start();
    }
}
