package cn.aezo.javase.msb.c04_1_t1_monitoring_t2;

/**
 * 题目：t1线程负责打印1-10，t2线程负责监控；当t1打印到5时，t2进行提示并结束
 *
 * 结果：
 *
 * 0
 * 1
 * 2
 * 3
 * 4
 * 5
 * 打印到5...
 * 6
 * 7
 * 8
 * 9
 *
 * @author smalle
 * @date 2020-06-02 22:04
 */
public class T1_wait_notify {
    private static volatile int num = 0;

    public static void main(String[] args) throws InterruptedException {

        final Object o = new Object();
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                System.out.println(i);
                if(i == 5) {
                    num = i;

                    synchronized (o) {
                        try {
                            o.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });

        Thread t2 = new Thread(() -> {
            while (true) {
                if(num == 5) {
                    System.out.println("打印到5...");
                    synchronized (o) {
                        o.notify();
                    }
                    break;
                }
            }
        });

        t1.start();
        t2.start();
    }
}
