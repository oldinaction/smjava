package cn.aezo.javase.msb.c03_3_CountDownLatch;

import java.util.concurrent.CountDownLatch;

/**
 * 输出如：
 * Thread-0
 * Thread-2
 * Thread-1
 * end...
 *
 * @author smalle
 * @date 2020-05-31 18:15
 */
public class T01_CountDownLatch {

    public static void main(String[] args) throws InterruptedException {

        CountDownLatch latch = new CountDownLatch(3);

        for (int i = 0; i < 3; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName());

                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                latch.countDown();
            }).start();
        }

        latch.await();

        System.out.println("end...");
    }

}
