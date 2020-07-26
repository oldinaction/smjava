package cn.aezo.concurrent_msb.c03_2_ReentrantLock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 可进行尝试获取锁
 *
 * 输出：
 * t1 start
 * t2 end, yesLock: false
 * t1 end
 *
 * @author smalle
 * @date 2020-05-27 23:25
 */
public class T3_ReentrantLock_tryLock {
    
    public static void main(String[] args) {
        Lock lock = new ReentrantLock();

        Thread t1 = new Thread(()->{
            try {
                lock.lock();
                System.out.println("t1 start");
                try {
                    TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("t1 end");
            } finally {
                lock.unlock();
            }
        }, "t1");
        t1.start();

        Thread t2 = new Thread(()->{
            boolean yesLock = false;
            try {
                try {
                    // 在3s内尝试获取锁
                    yesLock = lock.tryLock(3, TimeUnit.SECONDS);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("t2 end, yesLock: " + yesLock);
            } finally {
                if(yesLock) {
                    lock.unlock();
                }
            }
        }, "t2");
        t2.start();
    }

}
