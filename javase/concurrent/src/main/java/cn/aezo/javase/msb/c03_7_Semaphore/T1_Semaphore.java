package cn.aezo.javase.msb.c03_7_Semaphore;

import java.util.concurrent.Semaphore;

/**
 * Semaphore信号灯，获取到信号灯(同时信号灯数量-1)的线程才可运行，释放信号灯(同时信号灯数量+1)了之后可供其他行程使用。使用场景如限流
 *
 * 结果如：1秒后同时打印下面3行，然后过了1秒再打印了第4行(有一个线程已经释放了信号灯)
 *
 * 获取到信号灯，执行线程： Thread-0
 * 获取到信号灯，执行线程： Thread-1
 * 获取到信号灯，执行线程： Thread-2
 * 获取到信号灯，执行线程： Thread-3
 *
 * @author smalle
 * @date 2020-06-02 21:22
 */
public class T1_Semaphore {

    public static void main(String[] args) {

        Semaphore semaphore = new Semaphore(3); // 信号灯的个数为3

        for (int i = 0; i < 4; i++) {
            new Thread(() -> {
                try {
                    semaphore.acquire();

                    Thread.sleep(1000);
                    System.out.println("获取到信号灯，执行线程： " + Thread.currentThread().getName());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    semaphore.release();
                }
            }).start();
        }

    }
}
