package cn.aezo.javase.msb.c02_1_volatile;

/**
 * volatile不能替代synchronized来保证线程安全
 *
 * @author smalle
 * @date 2020-05-31 15:07
 */
public class T01_volatile2 {
    private volatile static int count = 0;
    private static final Object o = new Object();

    public static void main(String[] args) throws InterruptedException {
        Thread[] threads = new Thread[100];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(() -> {
                // synchronized (o) {
                    for (int j = 0; j < 100000; j++) {
                        count++;
                    }
                // }
            });
        }
        for (int i = 0; i < threads.length; i++) {
            threads[i].start();
        }
        for (int i = 0; i < threads.length; i++) {
            threads[i].join();
        }
        System.out.println("count = " + count);
    }

}
