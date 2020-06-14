package cn.aezo.designpattern.c06_proxy.t1_static_proxy_v2;

/**
 * 静态代理
 *
 * 结果如：
 *
 * pre...
 * move...
 * move time: 446
 * post...
 *
 * @author smalle
 * @date 2020-06-09 22:32
 */
public class Main {

    public static void main(String[] args) {
        // 此处可嵌套，类似装饰器
        MovableLogProxy proxy = new MovableLogProxy(new MovableTimeProxy(new Dog()));
        proxy.move();
    }
}
