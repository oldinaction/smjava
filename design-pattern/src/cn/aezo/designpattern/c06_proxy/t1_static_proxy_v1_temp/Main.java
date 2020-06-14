package cn.aezo.designpattern.c06_proxy.t1_static_proxy_v1_temp;

/**
 * 静态代理
 *
 * 结果：
 *
 * pre...
 * move...
 * post...
 *
 * @author smalle
 * @date 2020-06-09 22:32
 */
public class Main {

    public static void main(String[] args) {
        DogProxy proxy = new DogProxy();
        proxy.move();
    }
}
