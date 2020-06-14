package cn.aezo.designpattern.c07_adapter.t3_two_way_adapter;

/**
 * @author smalle
 * @date 2020-06-09 23:11
 */
public class ObjectAdapter implements ITarget, IAdaptee {
    private IAdaptee adaptee;
    private ITarget target;

    public ObjectAdapter(Adaptee adaptee) {
        this.adaptee = adaptee;
    }

    public ObjectAdapter(ITarget target) {
        this.target = target;
    }

    @Override
    public String request() {
        return adaptee.specialRequest();
    }

    @Override
    public String specialRequest() {
        return target.request();
    }
}
