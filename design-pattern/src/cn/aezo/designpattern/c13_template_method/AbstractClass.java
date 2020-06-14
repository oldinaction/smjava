package cn.aezo.designpattern.c13_template_method;

/**
 * @author smalle
 * @date 2020-06-13 17:03
 */
public abstract class AbstractClass {

    public void templateMethod() {
        abstractMethod1();

        specificMethod();

        abstractMethod2();
    }

    private void specificMethod() {
        System.out.println("specificMethod...");
    }

    public abstract void abstractMethod1();
    public abstract void abstractMethod2();
}
