package cn.aezo.designpattern.c08_bridge;

/**
 * @author smalle
 * @date 2020-06-13 08:14
 */
public class UserController implements Controller {

    private Service service;

    public UserController(Service service) {
        this.service = service;
    }

    @Override
    public void doPost() {
        System.out.println("doPost...");
        service.service();
    }
}
