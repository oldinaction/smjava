package cn.aezo.concurrent_msb.c03_9_LockSupport;

import java.util.concurrent.locks.LockSupport;

/**
 *
 * 结果：打印start...后，1s后再打印end...
 *
 * start...
 * end...
 *
 * @author smalle
 * @date 2020-06-02 21:53
 */
public class T1_LockSupport {
    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(() -> {
            System.out.println("start...");

            LockSupport.park();

            System.out.println("end...");
        });
        t.start();

        Thread.sleep(2000);

        LockSupport.unpark(t);
    }

}
