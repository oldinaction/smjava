package cn.aezo.designpattern.c07_adapter.t1_class_adapter;

/**
 * 类适配器：基于继承
 *
 * @author smalle
 * @date 2020-06-09 23:11
 */
public class ClassAdapter extends Adaptee implements Target {
    @Override
    public String request() {
        return super.specialRequest();
    }
}
