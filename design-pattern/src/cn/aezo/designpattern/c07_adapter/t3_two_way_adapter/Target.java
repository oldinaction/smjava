package cn.aezo.designpattern.c07_adapter.t3_two_way_adapter;

/**
 * @author smalle
 * @date 2020-06-09 23:40
 */
public class Target implements ITarget {
    @Override
    public String request() {
        return "Target data...";
    }
}
