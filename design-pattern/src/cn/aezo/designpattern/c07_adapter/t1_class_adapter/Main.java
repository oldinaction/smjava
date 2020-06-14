package cn.aezo.designpattern.c07_adapter.t1_class_adapter;

/**
 * 类适配器：基于继承，耦合度高
 *
 * @author smalle
 * @date 2020-06-09 23:12
 */
public class Main {
    public static void main(String[] args) {
        Target target = new ClassAdapter();
        String data = target.request();
        System.out.println(data);
    }
}
