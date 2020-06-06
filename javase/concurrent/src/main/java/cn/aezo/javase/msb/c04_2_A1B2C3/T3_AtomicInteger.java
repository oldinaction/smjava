package cn.aezo.javase.msb.c04_2_A1B2C3;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author smalle
 * @date 2020-06-03 08:50
 */
public class T3_AtomicInteger {

    static String[] letter = new String[]{"A", "B", "C"};
    static String[] num = new String[]{"1", "2", "3"};

    static AtomicInteger count = new AtomicInteger(1);

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            for (String s : letter) {
                while (count.get() != 1) {}
                System.out.println(s);
                count.set(2);
            }
        });

        Thread t2 = new Thread(() -> {
            for (String s : num) {
                while (count.get() != 2) {}
                System.out.println(s);
                count.set(1);
            }
        });

        t1.start();
        t2.start();

    }


}
