package cn.aezo.designpattern.c22_memento;

/**
 * @author smalle
 * @date 2020-06-14 11:38
 */
public class Caretaker {
    private Memento memento;

    public Memento getMemento() {
        return memento;
    }

    public void setMemento(Memento memento) {
        this.memento = memento;
    }
}
