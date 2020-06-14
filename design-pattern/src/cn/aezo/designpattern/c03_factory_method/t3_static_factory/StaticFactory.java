package cn.aezo.designpattern.c03_factory_method.t3_static_factory;

import cn.aezo.designpattern.c03_factory_method.t1_factory_method.Cat;
import cn.aezo.designpattern.c03_factory_method.t1_factory_method.Dog;

/**
 * 静态工厂
 *
 * @author smalle
 * @date 2020-06-09 20:57
 */
public class StaticFactory {

    public static Cat newCat() {
        return new Cat();
    }

    public static Dog newDog() {
        return new Dog();
    }

    public static void main(String[] args) {
        StaticFactory.newCat().move();
        StaticFactory.newDog().move();
    }

}
