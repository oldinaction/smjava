package cn.aezo.designpattern.c07_adapter.t3_two_way_adapter;

/**
 * 双向适配器
 *
 * @author smalle
 * @date 2020-06-09 23:12
 */
public class Main {
    public static void main(String[] args) {
        // 被适配者 Adaptee
        Adaptee adaptee1 = new Adaptee();
        ITarget target1 = new ObjectAdapter(adaptee1);
        String data1 = target1.request();
        System.out.println(data1);

        // 被适配者 Target
        Target target2 = new Target();
        IAdaptee adaptee2 = new ObjectAdapter(target2);
        String data2 = adaptee2.specialRequest();
        System.out.println(data2);
    }
}
