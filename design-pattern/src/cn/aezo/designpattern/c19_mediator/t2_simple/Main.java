package cn.aezo.designpattern.c19_mediator.t2_simple;

/**
 * 结果：
 *
 * 具体同事类A'发出请求...
 * 具体同事类B'收到请求...
 * ---------------
 * 具体同事类B'发出请求...
 * 具体同事类A'收到请求...
 *
 * @author smalle
 * @date 2020-06-14 09:59
 */
public class Main {
    public static void main(String[] args) {
        Colleague colleague1 = new ConcreteColleagueA();
        Colleague colleague2 = new ConcreteColleagueB();

        colleague1.send();
        System.out.println("---------------");
        colleague2.send();
    }
}
