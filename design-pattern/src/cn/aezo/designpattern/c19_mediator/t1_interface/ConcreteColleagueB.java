package cn.aezo.designpattern.c19_mediator.t1_interface;

/**
 * @author smalle
 * @date 2020-06-14 09:53
 */
public class ConcreteColleagueB extends Colleague {
    @Override
    public void receive() {
        System.out.println("具体同事类B收到请求...");
    }

    @Override
    public void send() {
        System.out.println("具体同事类B发出请求...");
        mediator.relay(this); // 请中介者转发
    }
}
