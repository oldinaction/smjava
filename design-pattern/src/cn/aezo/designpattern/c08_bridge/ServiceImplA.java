package cn.aezo.designpattern.c08_bridge;

/**
 * @author smalle
 * @date 2020-06-13 08:12
 */
public class ServiceImplA implements Service {
    @Override
    public void service() {
        System.out.println("ServiceImplA...");
    }
}
