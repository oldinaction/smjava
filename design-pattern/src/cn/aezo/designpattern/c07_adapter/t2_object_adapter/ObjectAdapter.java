package cn.aezo.designpattern.c07_adapter.t2_object_adapter;

/**
 * 对象适配器：基于依赖，相对类适配器耦合度更低
 *
 * @author smalle
 * @date 2020-06-09 23:11
 */
public class ObjectAdapter implements Target {
    private Adaptee adaptee;

    public ObjectAdapter(Adaptee adaptee) {
        this.adaptee = adaptee;
    }

    @Override
    public String request() {
        return adaptee.specialRequest();
    }
}
