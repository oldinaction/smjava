package cn.aezo.designpattern.c06_proxy.t1_static_proxy_v2;

/**
 * @author smalle
 * @date 2020-06-09 22:28
 */
public class MovableLogProxy implements Movable {
    private Movable movable;

    public MovableLogProxy(Movable movable) {
        this.movable = movable;
    }

    @Override
    public void move() {
        this.preMove();
        movable.move();
        this.postMove();
    }

    private void preMove() {
        System.out.println("pre...");
    }

    private void postMove() {
        System.out.println("post...");
    }
}
