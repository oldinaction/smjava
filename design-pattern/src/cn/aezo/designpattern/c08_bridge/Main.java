package cn.aezo.designpattern.c08_bridge;

/**
 * @author smalle
 * @date 2020-06-13 08:16
 */
public class Main {

    public static void main(String[] args) {
        Service serviceA = new ServiceImplA();
        UserController controller1 = new UserController(serviceA);
        controller1.doPost();

        Service serviceB = new ServiceImplB();
        UserController controller2 = new UserController(serviceB);
        controller2.doPost();
    }
}
