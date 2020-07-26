package cn.aezo.concurrent_msb.c03_5_Phaser;

import java.util.concurrent.Phaser;

/**
 * 打印如：
 * 1.arrived: Visitor-1
 * 1.arrived: Visitor-3
 * 1.arrived: Visitor-4
 * 1.arrived: Visitor-2
 * 1.arrived: Visitor-0
 * 1.arrived: NewPeople-0
 * 1.arrived: NewPeople-1
 * 2.eatOver: Visitor-2
 * 2.eatOver: Visitor-0
 * 2.eatOver: NewPeople-0
 * 2.eatOver: Visitor-3
 * 2.eatOver: Visitor-4
 * 2.eatOver: NewPeople-1
 * 2.eatOver: Visitor-1
 * 3.kiss: NewPeople-1
 * 3.deregister: Visitor-4
 * 3.kiss: NewPeople-0
 * 3.deregister: Visitor-1
 * 3.deregister: Visitor-2
 * 3.deregister: Visitor-3
 * 3.deregister: Visitor-0
 *
 * @author smalle
 * @date 2020-06-01 12:56
 */
public class T1_Phaser {
    Phaser phaser = new Phaser();

    // 宾客到达目的地
    private void arrived() {
        System.out.println("1.arrived: " + Thread.currentThread().getName());
        sleep(1000);
        phaser.arriveAndAwaitAdvance();
    }

    // 吃完饭
    private void eatOver() {
        System.out.println("2.eatOver: " + Thread.currentThread().getName());
        sleep(1000);
        phaser.arriveAndAwaitAdvance();
    }

    // 新郎新娘洞房亲亲
    private void kiss() {
        String name = Thread.currentThread().getName();
        if(name.startsWith("NewPeople")) {
            System.out.println("3.kiss: " + Thread.currentThread().getName());
            sleep(1000);
            phaser.arriveAndAwaitAdvance();
        } else {
            System.out.println("3.deregister: " + Thread.currentThread().getName());
            sleep(1000);
            phaser.arriveAndDeregister();
        }
    }

    private void sleep(long s) {
        try {
            Thread.sleep(s);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        T1_Phaser o = new T1_Phaser();
        o.phaser.bulkRegister(7);

        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                o.arrived();
                o.eatOver();
                o.kiss();
            }, "Visitor-" + i).start();
        }

        for (int i = 0; i < 2; i++) {
            new Thread(() -> {
                o.arrived();
                o.eatOver();
                o.kiss();
            }, "NewPeople-" + i).start();
        }
    }

}
