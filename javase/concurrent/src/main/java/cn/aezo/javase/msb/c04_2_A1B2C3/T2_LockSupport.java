package cn.aezo.javase.msb.c04_2_A1B2C3;

import java.util.concurrent.locks.LockSupport;

/**
 * @author smalle
 * @date 2020-06-03 07:59
 */
public class T2_LockSupport {

    static String[] letter = new String[]{"A", "B", "C"};
    static String[] num = new String[]{"1", "2", "3"};
    static Thread t1 = null;
    static Thread t2 = null;

    public static void main(String[] args) {
        t1 = new Thread(() -> {
            for (String s : letter) {
                System.out.println(s);
                LockSupport.unpark(t2);
                LockSupport.park();
            }
            LockSupport.unpark(t2);
        });

        t2 = new Thread(() -> {
            LockSupport.park();
            for (String s : num) {
                System.out.println(s);
                LockSupport.unpark(t1);
                LockSupport.park();
            }
        });

        t1.start();
        t2.start();
    }

}
