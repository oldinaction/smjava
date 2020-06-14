package cn.aezo.designpattern.c18_observer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author smalle
 * @date 2020-06-13 18:36
 */
public abstract class Subject {
    protected List<IObserver> observers = new ArrayList<IObserver>();

    public void add(IObserver observer) {
        observers.add(observer);
    }

    public void remove(IObserver observer) {
        observers.remove(observer);
    }

    public abstract void notifyObserver(); // 通知观察者方法
}
