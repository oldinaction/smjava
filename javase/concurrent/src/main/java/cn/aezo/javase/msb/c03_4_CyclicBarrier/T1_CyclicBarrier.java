package cn.aezo.javase.msb.c03_4_CyclicBarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * 运行后，等待3s左右打印结果：
 * end...
 * Thread-0
 * Thread-1
 * Thread-2
 *
 * @author smalle
 * @date 2020-06-01 12:38
 */
public class T1_CyclicBarrier {

    public static void main(String[] args) throws InterruptedException {
        CyclicBarrier barrier = new CyclicBarrier(3);

        for (int i = 0; i < 3; i++) {
            Thread.sleep(1000);

            new Thread(() -> {
                try {
                    barrier.await(); // 将此线程加入到列车，然后等待所有的(3个)线程加入到列车，再发车(全部继续执行)
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }

                System.out.println(Thread.currentThread().getName());
            }).start();
        }

        System.out.println("end...");
    }
}
