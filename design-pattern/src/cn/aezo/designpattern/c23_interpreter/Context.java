package cn.aezo.designpattern.c23_interpreter;

/**
 * @author smalle
 * @date 2020-06-14 11:55
 */
public class Context {
    private Expression cityPerson;

    public Context() {
        String[] citys = {"上海", "广州"};
        String[] persons = {"老人", "妇女", "儿童"};
        Expression city = new TerminalExpression(citys);
        Expression person = new TerminalExpression(persons);
        cityPerson = new AndExpression(city, person);
    }

    public void freeRide(String info) {
        boolean ok = cityPerson.interpret(info);
        if(ok) System.out.println("您是" + info + "，您本次乘车免费！");
        else System.out.println(info + "，您不是免费人员，本次乘车扣费2元！");
    }
}