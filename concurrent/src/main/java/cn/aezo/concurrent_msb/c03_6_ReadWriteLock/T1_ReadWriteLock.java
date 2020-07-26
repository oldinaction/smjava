package cn.aezo.concurrent_msb.c03_6_ReadWriteLock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 读锁(共享锁，多个线程可同时获得锁)，写锁(独占锁，同一个时刻只能一个线程拥有锁)
 *
 * 执行结果：read...基本同时打印出来，然后每过一秒打印一个write...
 *
 * read...
 * read...
 * read...
 * read...
 * read...
 * write...
 * write...
 * write...
 *
 * @author smalle
 * @date 2020-06-02 12:04
 */
public class T1_ReadWriteLock {
    static ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    static Lock readLock = readWriteLock.readLock(); // 获取读锁
    static Lock writeLock = readWriteLock.writeLock(); // 获取写锁

    public static void main(String[] args) {

        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                try {
                    readLock.lock();
                    System.out.println("read...");
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    readLock.unlock();
                }
            }).start();
        }

        for (int i = 0; i < 3; i++) {
            new Thread(() -> {
                try {
                    writeLock.lock();
                    System.out.println("write...");
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    writeLock.unlock();
                }
            }).start();
        }

    }
}
