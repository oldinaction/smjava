package cn.aezo.javase.msb.c01_1_thread;

/**
 * @author smalle
 * @date 2020-01-11 20:03
 *
 * Thread.State.NEW
 * Thread.State.RUNNABLE
 * Thread.State.TERMINATED
 * Thread.State.TIMED_WAITING
 * Thread.State.WAITING
 * Thread.State.BLOCKED
 */
public class T04_ThreadState {

    public static void main(String[] args) {
        Thread t = new Thread(()->{
            for (int i = 0; i < 10; i++) {
                System.out.println("t...");
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "t");
        System.out.println("1---" + t.getState());

        t.start();
        System.out.println("2---" + t.getState());

        try {
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("3---" + t.getState());
    }
}

