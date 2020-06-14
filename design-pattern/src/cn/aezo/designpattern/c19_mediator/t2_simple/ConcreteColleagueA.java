package cn.aezo.designpattern.c19_mediator.t2_simple;

/**
 * @author smalle
 * @date 2020-06-14 09:53
 */
public class ConcreteColleagueA implements Colleague {
    public ConcreteColleagueA() {
        SimpleMediator.register(this);
    }

    @Override
    public void receive() {
        System.out.println("具体同事类A'收到请求...");
    }

    @Override
    public void send() {
        System.out.println("具体同事类A'发出请求...");
        SimpleMediator.relay(this); // 请中介者转发
    }
}
