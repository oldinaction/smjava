package cn.aezo.javase.msb.c01_1_thread;

/**
 * @author smalle
 * @date 2020-01-11 18:37
 *
 */
public class T03_Sleep_Yield_Join {

    public static void main(String[] args) {
        // Thread.sleep 睡眠一定时间
        // testSleep();

        // Thread.yield 让出CPU一小会
        // testYield();

        // t.join 常用于等待某各线程执行结束，如等待t线程执行结束
        testJoin();
    }

    static void testSleep() {
        try {
            // sleep的是当前线程
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("end..");
    }

    static void testYield() {
        new Thread(()->{
            for(int i=0; i<100; i++) {
                System.out.println("A" + i);
                if(i%10 == 0) Thread.yield();
            }
        }).start();

        new Thread(()->{
            for(int i=0; i<100; i++) {
                System.out.println("------------B" + i);
                if(i%10 == 0) Thread.yield();
            }
        }).start();
    }

    static void testJoin() {
        Thread t1 = new Thread(()->{
            for(int i=0; i<20; i++) {
                System.out.println("A" + i);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread t2 = new Thread(()->{
            for(int i=0; i<20; i++) {
                System.out.println("---B" + i);
                try {
                    if(i==5) {
                        t1.join();
                    }

                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        t1.start();
        t2.start();
    }
}
