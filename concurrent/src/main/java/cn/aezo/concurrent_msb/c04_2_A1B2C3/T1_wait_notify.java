package cn.aezo.concurrent_msb.c04_2_A1B2C3;

/**
 * 题目：t1、t2两个线程，t1线程负责打印A-Z，t2线程负责打印1-26，如何交替打印A1B2...Z26
 *
 * @author smalle
 * @date 2020-06-02 23:21
 */
public class T1_wait_notify {
    static String[] letter = new String[]{"A", "B", "C"};
    static String[] num = new String[]{"1", "2", "3"};
    final static Object o = new Object();
    volatile static int threadNum = 1; // 当前允许运行的线程

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            for (String s : letter) {
                synchronized (o) {
                    System.out.println(s);
                    o.notify();
                    threadNum = 2;
                    try {
                        o.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    o.notify(); // 防止t2打印完最后一个便一直进入wait无法被唤醒
                }
            }
        });

        Thread t2 = new Thread(() -> {
            for (String s : num) {
                while (threadNum == 1) {
                    synchronized (o) {
                        try {
                            o.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }

                synchronized (o) {
                    System.out.println(s);
                    o.notify();
                    threadNum = 1;
                    try {
                        o.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        t2.start();
        t1.start();
    }

}
