package cn.aezo.concurrent_msb.c03_5_Phaser;

import java.util.concurrent.Phaser;

/**
 * Phaser分层
 *
 * 打印结果如：
 * ---------------PHASE[0],Parties[5] ---------------
 * Thread-8: 执行完任务
 * Thread-2: 执行完任务
 * Thread-5: 执行完任务
 * Thread-9: 执行完任务
 * Thread-7: 执行完任务
 * Thread-4: 执行完任务
 * Thread-3: 执行完任务
 * Thread-0: 执行完任务
 * Thread-1: 执行完任务
 * Thread-6: 执行完任务
 * ---------------PHASE[1],Parties[5] ---------------
 * Thread-6: 执行完任务
 * Thread-7: 执行完任务
 * ...
 * ---------------PHASE[2],Parties[5] ---------------
 * ...
 *
 * @author smalle
 * @date 2020-06-02 10:02
 */
public class T3_Phaser_Tiering {
    private static final int TASKS_PER_PHASER = 2; // 每个Phaser对象对应的工作线程（任务）数

    public static void main(String[] args) {

        int repeats = 3; // 指定任务最多执行的次数
        // 根phaser，registeredParties参与者数为子phaser，只有当所有子phaser全部就绪才会放行
        // 此案例子phaser的参与者为phaser.register()注册的线程，只有当所有线程就绪，此子phaser会通知父phaser
        // 因此放行流程为：某个子phaser参与者准备就绪 -> 该子phaser通知父phaser -> 所有子phaser都就绪 -> 根phaser放行
        Phaser phaser = new Phaser() {
            // 返回true则Phaser终止
            @Override
            protected boolean onAdvance(int phase, int registeredParties) {
                System.out.println("---------------PHASE[" + phase + "],Parties[" + registeredParties + "] ---------------");
                return phase + 1 >= repeats || registeredParties == 0;
            }
        };

        Tasker[] taskers = new Tasker[10];
        build(taskers, 0, taskers.length, phaser); // 根据任务数, 为每个任务分配Phaser对象

        for (int i = 0; i < taskers.length; i++) {
            new Thread(taskers[i]).start();
        }
    }

    // 会分成3个子层级：0-1、2-3、4-5、6-7、8-9
    private static void build(Tasker[] taskers, int lower, int high, Phaser phaser) {
        if (high - lower > TASKS_PER_PHASER) {
            // 超过Phaser设定的处理数，需要细分
            for (int i = lower; i < high; i += TASKS_PER_PHASER) {
                int j = Math.min(i + TASKS_PER_PHASER, high);
                // 创建子层级Phaser，并传入父对象引用
                build(taskers, i, j, new Phaser(phaser));
            }
        } else {
            for (int i = lower; i < high; ++i)
                taskers[i] = new Tasker(phaser);
        }

    }

    static class Tasker implements Runnable {
        private final Phaser phaser;

        Tasker(Phaser phaser) {
            this.phaser = phaser;
            // 注册
            this.phaser.register();
        }

        @Override
        public void run() {
            // 只要Phaser没有终止, 各个线程的任务就会一直执行
            while (!phaser.isTerminated()) {
                phaser.arriveAndAwaitAdvance();
                // do something
                System.out.println(Thread.currentThread().getName() + ": 执行完任务");
            }
        }
    }
}
