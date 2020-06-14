package cn.aezo.designpattern.c06_proxy.t1_static_proxy_v1_temp;

/**
 * @author smalle
 * @date 2020-06-09 22:28
 */
public class DogProxy implements Movable {
    // 此处基于实际类Dog进行编程，耦合太高。可改成基于Movable，参考下一版本
    private Dog dog;

    @Override
    public void move() {
        if (dog == null) {
            dog = new Dog();
        }

        this.preMove();
        dog.move();
        this.postMove();
    }

    private void preMove() {
        System.out.println("pre...");
    }

    private void postMove() {
        System.out.println("post...");
    }
}
