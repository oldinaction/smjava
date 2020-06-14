package cn.aezo.designpattern.c04_abstract_factory;

/**
 * @author smalle
 * @date 2020-06-09 21:20
 */
public class Cat implements Farm {
    @Override
    public void grouth() {
        System.out.println("猫长大了...");
    }
}
