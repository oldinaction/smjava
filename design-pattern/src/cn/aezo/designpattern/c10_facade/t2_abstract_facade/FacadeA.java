package cn.aezo.designpattern.c10_facade.t2_abstract_facade;

/**
 * @author smalle
 * @date 2020-06-13 08:56
 */
public class FacadeA implements AbastractFacade {
    private SubSystemA subSystemA = new SubSystemA();
    private SubSystemB subSystemB = new SubSystemB();

    public void service() {
        System.out.println("接受到客户请求...");

        subSystemA.serviceA();
        subSystemB.serviceB();

        System.out.println("向客户反馈结果...");
    }
}
