package cn.aezo.designpattern.c19_mediator.t1_interface;

/**
 * 结果：
 *
 * 具体同事类A发出请求...
 * 具体同事类B收到请求...
 * ---------------
 * 具体同事类B发出请求...
 * 具体同事类A收到请求...
 *
 * @author smalle
 * @date 2020-06-14 09:59
 */
public class Main {
    public static void main(String[] args) {
        Mediator mediator = new ConcreteMediator();
        Colleague colleague1 = new ConcreteColleagueA();
        Colleague colleague2 = new ConcreteColleagueB();
        mediator.register(colleague1);
        mediator.register(colleague2);

        colleague1.send();
        System.out.println("---------------");
        colleague2.send();
    }
}
