package cn.aezo.designpattern.c04_abstract_factory;

/**
 * @author smalle
 * @date 2020-06-09 21:21
 */
public class Potato implements Farm {
    @Override
    public void grouth() {
        System.out.println("土豆长大了...");
    }
}
