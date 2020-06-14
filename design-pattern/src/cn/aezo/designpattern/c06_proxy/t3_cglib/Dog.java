package cn.aezo.designpattern.c06_proxy.t3_cglib;

import cn.aezo.designpattern.c06_proxy.t2_jdk_proxy.Movable;

/**
 * @author smalle
 * @date 2020-06-09 22:28
 */
public class Dog implements Movable {
    @Override
    public void move() {
        System.out.println("move...");
    }
}
