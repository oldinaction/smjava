package cn.aezo.designpattern.c07_adapter.t3_two_way_adapter;

/**
 * 被适配者
 *
 * @author smalle
 * @date 2020-06-09 23:10
 */
public class Adaptee implements IAdaptee {

    @Override
    public String specialRequest() {
        return "Adaptee data...";
    }
}
