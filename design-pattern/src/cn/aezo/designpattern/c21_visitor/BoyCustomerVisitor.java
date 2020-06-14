package cn.aezo.designpattern.c21_visitor;

/**
 * @author smalle
 * @date 2020-06-14 10:55
 */
public class BoyCustomerVisitor implements CustomerVisitor {
    @Override
    public void visit(Wine wine) {
        System.out.println("BoyCustomerVisitor喜欢酒");
    }

    @Override
    public void visit(Makeup makeup) {
        System.out.println("BoyCustomerVisitor不喜欢化妆品");
    }
}
