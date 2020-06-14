package cn.aezo.designpattern.c04_abstract_factory;

/**
 * @author smalle
 * @date 2020-06-09 21:18
 */
public class Dog implements Farm {
    @Override
    public void grouth() {
        System.out.println("狗长大了...");
    }
}
