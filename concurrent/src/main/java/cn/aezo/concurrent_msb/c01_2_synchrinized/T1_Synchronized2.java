package cn.aezo.concurrent_msb.c01_2_synchrinized;

/**
 * @author smalle
 * @date 2020-01-11 21:41
 */
public class T1_Synchronized2 {
    private static int count = 0;
    private static final Object o = new Object();

    public static void main(String[] args) throws InterruptedException {
        Thread[] threads = new Thread[100];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(() -> {
                synchronized (o) {
                    for (int j = 0; j < 100000; j++) {
                        count++;
                    }
                }
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
