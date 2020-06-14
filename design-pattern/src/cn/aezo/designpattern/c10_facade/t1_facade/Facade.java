package cn.aezo.designpattern.c10_facade.t1_facade;

/**
 * @author smalle
 * @date 2020-06-13 08:56
 */
public class Facade {
    private SubSystemA subSystemA = new SubSystemA();
    private SubSystemB subSystemB = new SubSystemB();
    private SubSystemC subSystemC = new SubSystemC();

    public void service() {
        System.out.println("接受到客户请求...");

        subSystemA.serviceA();
        subSystemB.serviceB();
        subSystemC.serviceC();

        System.out.println("向客户反馈结果...");
    }
}
