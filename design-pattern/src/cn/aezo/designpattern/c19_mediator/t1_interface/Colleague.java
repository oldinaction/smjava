package cn.aezo.designpattern.c19_mediator.t1_interface;

/**
 * @author smalle
 * @date 2020-06-14 09:51
 */
public abstract class Colleague {
    protected Mediator mediator;

    public void setMediator(Mediator mediator) {
        this.mediator = mediator;
    }

    public abstract void receive();
    public abstract void send();

}
