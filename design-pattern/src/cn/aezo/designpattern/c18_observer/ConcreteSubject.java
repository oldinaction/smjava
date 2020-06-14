package cn.aezo.designpattern.c18_observer;

/**
 * @author smalle
 * @date 2020-06-13 18:40
 */
public class ConcreteSubject extends Subject {
    @Override
    public void notifyObserver() {
        System.out.println("具体目标发生改变...");

        for(Object obs : observers) {
            ((IObserver)obs).response();
        }
    }
}
