package cn.aezo.designpattern.c22_memento;

/**
 * @author smalle
 * @date 2020-06-14 11:33
 */
public class Memento {
    private String state;

    public Memento(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
