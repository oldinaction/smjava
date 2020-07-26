package cn.aezo.concurrent_msb.c03_5_Phaser;

import java.util.concurrent.Phaser;

/**
 * 继承Phaser，重写其onAdvance方法可在每阶段执行完后进行操作
 *
 * 打印如：
 * 1.arrived: Visitor-2
 * 1.arrived: Visitor-1
 * 1.arrived: Visitor-4
 * 1.arrived: Visitor-3
 * 1.arrived: Visitor-0
 * 1.arrived: NewPeople-1
 * 1.arrived: NewPeople-0
 * 所有人都到齐了 7
 *
 * 2.eatOver: NewPeople-0
 * 2.eatOver: NewPeople-1
 * 2.eatOver: Visitor-1
 * 2.eatOver: Visitor-2
 * 2.eatOver: Visitor-0
 * 2.eatOver: Visitor-3
 * 2.eatOver: Visitor-4
 * 吃完饭了 7
 *
 * 3.deregister: Visitor-4
 * 3.deregister: Visitor-1
 * 3.deregister: Visitor-2
 * 3.deregister: Visitor-3
 * 3.kiss: NewPeople-1
 * 3.kiss: NewPeople-0
 * 3.deregister: Visitor-0
 * 新郎新娘洞房亲亲 2
 *
 * @author smalle
 * @date 2020-06-02 07:40
 */
public class T2_Phaser_onAdvance {
    static Phaser phaser = new MarriagePhaser();

    public static void main(String[] args) {
        phaser.bulkRegister(7);

        for (int i = 0; i < 5; i++) {
            new Thread(new People("Visitor-" + i)).start();
        }

        for (int i = 0; i < 2; i++) {
            new Thread(new People("NewPeople-" + i)).start();
        }
    }

    static class  MarriagePhaser extends Phaser {
        // 返回true表示Phaser终止
        @Override
        protected boolean onAdvance(int phase, int registeredParties) {
            switch (phase) {
                case 0:
                    System.out.println("所有人都到齐了 " + registeredParties + "\n");
                    return false;
                case 1:
                    System.out.println("吃完饭了 " + registeredParties + "\n");
                    return false;
                case 2:
                    System.out.println("新郎新娘洞房亲亲 " + registeredParties + "\n");
                    return true;
                default:
                    return true;
            }
        }
    }

    static class People implements Runnable {
        String name;

        public People(String name) {
            this.name = name;
        }

        // 宾客到达目的地
        private void arrived() {
            System.out.println("1.arrived: " + name);
            sleep(1000);
            phaser.arriveAndAwaitAdvance();
        }

        // 吃完饭
        private void eatOver() {
            System.out.println("2.eatOver: " + name);
            sleep(1000);
            phaser.arriveAndAwaitAdvance();
        }

        // 新郎新娘洞房亲亲
        private void kiss() {
            if(name.startsWith("NewPeople")) {
                System.out.println("3.kiss: " + name);
                sleep(1000);
                phaser.arriveAndAwaitAdvance();
            } else {
                System.out.println("3.deregister: " + name);
                sleep(1000);
                phaser.arriveAndDeregister(); // 注销
            }
        }

        private void sleep(long s) {
            try {
                Thread.sleep(s);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void run() {
            arrived();

            eatOver();

            kiss();
        }
    }
}
