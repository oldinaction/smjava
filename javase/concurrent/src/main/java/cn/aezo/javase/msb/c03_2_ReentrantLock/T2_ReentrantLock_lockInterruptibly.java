package cn.aezo.javase.msb.c03_2_ReentrantLock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ReentrantLock可被打断
 * 此时由于t1比t2先启动，从而先获得锁，而t2需要获得同一把锁(lock)，因此需要等待，直到t1被打断释放锁
 *
 * 输出：
 * t1 start
 * interrupted t1!
 * t1 unlock
 * t2 start
 * interrupted t2!
 * t2 unlock
 *
 * @author smalle
 * @date 2020-05-27 23:25
 */
public class T2_ReentrantLock_lockInterruptibly {

    public static void main(String[] args) {
        Lock lock = new ReentrantLock();

        Thread t1 = new Thread(()->{
            try {
                lock.lock(); // 实际测试，也可以对interrupt()方法做出响应
                System.out.println("t1 start");
                TimeUnit.SECONDS.sleep(Integer.MAX_VALUE);
                System.out.println("t1 end");
            } catch (InterruptedException e) {
                System.out.println("interrupted t1!");
            } finally {
                // ((ReentrantLock) lock).isLocked() // 只能判断这把锁是否已经被使用，不能判断当前线程是否获得这把锁
                System.out.println("t1 unlock");
                lock.unlock();
            }
        }, "t1");
        t1.start();

        Thread t2 = new Thread(()->{
            try {
                lock.lockInterruptibly(); // 可以对interrupt()方法做出响应
                System.out.println("t2 start");
                TimeUnit.SECONDS.sleep(5);
                System.out.println("t2 end");
            } catch (InterruptedException e) {
                System.out.println("interrupted t2!");
            } finally {
                System.out.println("t2 unlock");
                lock.unlock();  // 只有在获得锁后再解锁，否则会报异常 IllegalMonitorStateException
            }
        }, "t2");
        t2.start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t1.interrupt();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t2.interrupt();
    }

}
