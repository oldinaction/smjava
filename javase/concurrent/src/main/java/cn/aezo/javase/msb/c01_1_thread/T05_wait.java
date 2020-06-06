package cn.aezo.javase.msb.c01_1_thread;

/**
 * @author smalle
 * @date 2020-06-02 22:52
 */
public class T05_wait {
    private static volatile boolean running = true;

    public static void main(String[] args) {
        // waitAboutLock();

        // notifyBefore();
    }

    /**
     * 线程被唤醒后，需要重新获取锁才能继续执行
     *
     * 结果：第一行打印后，过了1秒打印第二行，再过了2秒，同时打印最后3句
     *
     * t1 run...
     * t2 run...
     * t2 end...
     * t1被唤醒，t1重新获得锁，继续执行
     * t1 end...
     *
     */
    private static void waitAboutLock() {
        Thread[] threads = ready();

        // 第一个线程先执行
        threads[0].start();

        // 需要保证线程t1先执行(wait要在notify之前运行)
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        threads[1].start();
    }

    /**
     * notify 和 wait 的顺序不能错，如果A线程先执行notify方法，B线程再执行wait方法，那么B线程是无法被唤醒的(不会报错，LockSupport得unpark可在park之前运行)
     *
     * 结果：程序不会结束
     *
     * t2 run...
     * t1 run...
     * t2 end...
     */
    private static void notifyBefore() {
        Thread[] threads = ready();

        // 第二个线程先执行(先执行notify)
        threads[1].start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        threads[0].start();
    }

    /**
     * 使用wait/notify/notifyAll时需要先获取锁
     */
    private static Thread[] ready() {
        final Object o = new Object();

        Thread t1 = new Thread(() -> {
            System.out.println("t1 run...");
            synchronized (o) {
                try {
                    o.wait();
                    System.out.println("t1被唤醒，t1重新获得锁，继续执行"); // 线程被唤醒后，需要重新获取锁才能继续执行
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("t1 end...");
        }, "t1");

        Thread t2 = new Thread(() -> {
            System.out.println("t2 run...");
            synchronized (o) {
                o.notify();

                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println("t2 end...");
            }
        }, "t2");

        return new Thread[]{t1, t2};
    }
}
