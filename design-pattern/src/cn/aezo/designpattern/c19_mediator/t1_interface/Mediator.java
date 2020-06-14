package cn.aezo.designpattern.c19_mediator.t1_interface;

/**
 * Created by smalle on 2020-06-14 09:50.
 */
public interface Mediator {
    void register(Colleague c);
    void relay(Colleague c); // 转发
}
