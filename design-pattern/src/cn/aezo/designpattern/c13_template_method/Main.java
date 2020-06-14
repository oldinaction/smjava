package cn.aezo.designpattern.c13_template_method;

/**
 * 结果：
 *
 * method1...
 * specificMethod...
 * method2...
 *
 * @author smalle
 * @date 2020-06-13 17:06
 */
public class Main {

    public static void main(String[] args) {
        AbstractClass abstractClass = new ConcreteClass();
        abstractClass.templateMethod();
    }
}
