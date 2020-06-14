package cn.aezo.designpattern.c21_visitor;

/**
 * @author smalle
 * @date 2020-06-14 10:57
 */
public class Main {
    public static void main(String[] args) {
        BoyCustomerVisitor boyCustomerVisitor = new BoyCustomerVisitor();
        GirlCustomerVisitor girlCustomerVisitor = new GirlCustomerVisitor();

        Goods wine = new Wine();
        Goods makeup = new Makeup();

        wine.accept(boyCustomerVisitor);
        makeup.accept(boyCustomerVisitor);

        System.out.println("------------");

        wine.accept(girlCustomerVisitor);
        makeup.accept(girlCustomerVisitor);
    }
}
