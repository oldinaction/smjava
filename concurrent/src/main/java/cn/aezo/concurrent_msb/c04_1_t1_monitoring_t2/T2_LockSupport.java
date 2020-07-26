package cn.aezo.concurrent_msb.c04_1_t1_monitoring_t2;

import java.util.concurrent.locks.LockSupport;

/**
 * @author smalle
 * @date 2020-06-02 23:21
 */
public class T2_LockSupport {
    private static volatile int num = 0;

    public static void main(String[] args) {

        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                System.out.println(i);
                if(i == 5) {
                    num = i;
                    LockSupport.park();
                }
            }
        });

        Thread t2 = new Thread(() -> {
            while (true) {
                if(num == 5) {
                    System.out.println("打印到5...");
                    LockSupport.unpark(t1);
                    break;
                }
            }
        });

        t1.start();
        t2.start();
    }
}
