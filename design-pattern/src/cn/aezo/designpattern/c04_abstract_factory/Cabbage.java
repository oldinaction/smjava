package cn.aezo.designpattern.c04_abstract_factory;

/**
 * @author smalle
 * @date 2020-06-09 21:21
 */
public class Cabbage implements Farm {
    @Override
    public void grouth() {
        System.out.println("白菜长大了...");
    }
}
