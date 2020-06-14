package cn.aezo.designpattern.c03_factory_method.t1_factory_method;

/**
 * @author smalle
 * @date 2020-06-09 20:40
 */
public class Dog implements Animal {
    @Override
    public void move() {
        System.out.println("狗跑...");
    }
}
