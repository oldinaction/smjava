package cn.aezo.designpattern.c10_facade.t2_abstract_facade;

/**
 * 当增加或移除子系统时需要修改外观类，这违背了"开闭原则"。此时引入抽象外观类，则在一定程度上解决了该问题
 *
 * @author smalle
 * @date 2020-06-13 08:57
 */
public class Main {

    public static void main(String[] args) {

        FacadeA facade = new FacadeA();
        facade.service();

        System.out.println();
        FacadeB facade2 = new FacadeB();
        facade2.service();
    }
}
