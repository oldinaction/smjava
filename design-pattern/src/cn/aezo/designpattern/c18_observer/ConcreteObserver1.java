package cn.aezo.designpattern.c18_observer;

/**
 * @author smalle
 * @date 2020-06-13 18:38
 */
public class ConcreteObserver1 implements IObserver {
    @Override
    public void response() {
        System.out.println("ConcreteObserver1 response...");
    }
}
