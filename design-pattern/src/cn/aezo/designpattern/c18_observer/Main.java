package cn.aezo.designpattern.c18_observer;

/**
 * @author smalle
 * @date 2020-06-13 18:39
 */
public class Main {

    public static void main(String[] args) {
        IObserver observer1 = new ConcreteObserver1();
        IObserver observer2 = new ConcreteObserver2();

        Subject subject = new ConcreteSubject();
        subject.add(observer1);
        subject.add(observer2);

        subject.notifyObserver();
    }
}
