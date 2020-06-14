package cn.aezo.designpattern.c09_decorator;

/**
 * @author smalle
 * @date 2020-06-13 08:48
 */
public class BaseHouse implements House {
    @Override
    public void build() {
        System.out.println("房子框架搭建完成");
    }
}
