package cn.aezo.designpattern.c23_interpreter;

/**
 * 文法规则：
 *
 * <expression> ::= <city>的<person>
 * <city> ::= 上海|广州
 * <person> ::= 老人|妇女|儿童
 *
 * 结果：
 *
 * 您是上海的老人，您本次乘车免费！
 * 上海的年轻人，您不是免费人员，本次乘车扣费2元！
 * 您是广州的妇女，您本次乘车免费！
 * 您是广州的儿童，您本次乘车免费！
 * 山东的儿童，您不是免费人员，本次乘车扣费2元！
 *
 * @author smalle
 * @date 2020-06-14 11:56
 */
public class Main {
    public static void main(String[] args) {
        Context bus = new Context();
        bus.freeRide("上海的老人");
        bus.freeRide("上海的年轻人");
        bus.freeRide("广州的妇女");
        bus.freeRide("广州的儿童");
        bus.freeRide("山东的儿童");
    }
}
