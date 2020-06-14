package cn.aezo.designpattern.c21_visitor;

/**
 * 商品接受顾客评价
 *
 * Created by smalle on 2020-06-14 10:53.
 */
public interface Goods {
    void accept(CustomerVisitor customerVisitor);
}
