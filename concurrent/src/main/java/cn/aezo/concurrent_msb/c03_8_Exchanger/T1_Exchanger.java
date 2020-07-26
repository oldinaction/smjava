package cn.aezo.concurrent_msb.c03_8_Exchanger;

import java.util.concurrent.Exchanger;

/**
 * Exchanger交换器，两个线程之间数据交换
 *
 * 结果如：1秒之后同时打印
 *
 * t1 receive: hi t1...
 * t2 receive: hello t2...
 *
 * @author smalle
 * @date 2020-06-02 21:36
 */
public class T1_Exchanger {
    static Exchanger<String> exchanger = new Exchanger<>();

    public static void main(String[] args) {

        new Thread(() -> {
            try {
                Thread.sleep(1000);

                String ret = exchanger.exchange("hello t2...");
                System.out.println(Thread.currentThread().getName() + " receive: " + ret);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "t1").start();

        new Thread(() -> {
            try {
                String ret = exchanger.exchange("hi t1...");
                System.out.println(Thread.currentThread().getName() + " receive: " + ret);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "t2").start();
    }
}
