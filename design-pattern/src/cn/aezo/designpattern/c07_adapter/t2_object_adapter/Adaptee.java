package cn.aezo.designpattern.c07_adapter.t2_object_adapter;

/**
 * 被适配者。一般由于各种原因不能修改其源码
 *
 * @author smalle
 * @date 2020-06-09 23:10
 */
public class Adaptee {

    public String specialRequest() {
        return "data...";
    }
}
