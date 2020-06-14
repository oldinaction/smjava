package cn.aezo.designpattern.c06_proxy.t1_static_proxy_v2;

import java.util.Random;

/**
 * @author smalle
 * @date 2020-06-09 22:28
 */
public class Dog implements Movable {
    @Override
    public void move() {
        System.out.println("move...");
        try {
            Thread.sleep(new Random().nextInt(1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
