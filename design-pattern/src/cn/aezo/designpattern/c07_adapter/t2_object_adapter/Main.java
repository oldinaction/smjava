package cn.aezo.designpattern.c07_adapter.t2_object_adapter;

/**
 * 对象适配器：基于依赖，相对类适配器耦合度更低。常用
 *
 * @author smalle
 * @date 2020-06-09 23:12
 */
public class Main {
    public static void main(String[] args) {
        Adaptee adaptee = new Adaptee();
        Target target = new ObjectAdapter(adaptee);
        String data = target.request();
        System.out.println(data);
    }
}
