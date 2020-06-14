package cn.aezo.designpattern.c13_template_method;

/**
 * @author smalle
 * @date 2020-06-13 17:05
 */
public class ConcreteClass extends AbstractClass {
    @Override
    public void abstractMethod1() {
        System.out.println("abstractMethod1...");
    }

    @Override
    public void abstractMethod2() {
        System.out.println("abstractMethod2...");
    }
}
