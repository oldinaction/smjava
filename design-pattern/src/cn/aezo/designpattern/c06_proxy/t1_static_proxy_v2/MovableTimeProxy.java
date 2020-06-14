package cn.aezo.designpattern.c06_proxy.t1_static_proxy_v2;

/**
 * @author smalle
 * @date 2020-06-09 22:28
 */
public class MovableTimeProxy implements Movable {
    private Movable movable;

    public MovableTimeProxy(Movable movable) {
        this.movable = movable;
    }

    @Override
    public void move() {
        long start = System.currentTimeMillis();
        movable.move();
        long end = System.currentTimeMillis();
        System.out.println("move time: " + (end - start));
    }
}
