package cn.aezo.javase.msb.c04_2_A1B2C3;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author smalle
 * @date 2020-06-03 08:50
 */
public class T4_ReentrantLock_Condition {

    static String[] letter = new String[]{"A", "B", "C"};
    static String[] num = new String[]{"1", "2", "3"};

    static ReentrantLock lock = new ReentrantLock();
    static Condition c1 = lock.newCondition();
    static Condition c2 = lock.newCondition();

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            try {
                lock.lock();
                for (String s : letter) {
                    System.out.println(s);
                    c2.signalAll(); // 不能使用c2.notifyAll
                    c1.await();
                }
                c2.signalAll();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        });

        Thread t2 = new Thread(() -> {
            try {
                lock.lock();
                for (String s : num) {
                    System.out.println(s);
                    c1.signalAll();
                    c2.await();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        });

        t1.start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        t2.start();

    }


}
