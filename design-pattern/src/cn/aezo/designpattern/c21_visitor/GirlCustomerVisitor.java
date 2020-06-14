package cn.aezo.designpattern.c21_visitor;

/**
 * @author smalle
 * @date 2020-06-14 10:55
 */
public class GirlCustomerVisitor implements CustomerVisitor {
    @Override
    public void visit(Wine wine) {
        System.out.println("GirlCustomerVisitor不喜欢酒");
    }

    @Override
    public void visit(Makeup makeup) {
        System.out.println("GirlCustomerVisitor喜欢化妆品");
    }
}
