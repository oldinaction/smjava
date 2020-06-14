package cn.aezo.designpattern.c21_visitor;

/**
 * 酒
 *
 * @author smalle
 * @date 2020-06-14 10:54
 */
public class Wine implements Goods {
    @Override
    public void accept(CustomerVisitor customerVisitor) {
        customerVisitor.visit(this);
    }
}
