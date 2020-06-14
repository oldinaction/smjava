package cn.aezo.designpattern.c03_factory_method.t2_simple_factory;

import cn.aezo.designpattern.c03_factory_method.t1_factory_method.Cat;
import cn.aezo.designpattern.c03_factory_method.t1_factory_method.Dog;

/**
 * 简单工厂
 *
 * @author smalle
 * @date 2020-06-09 20:55
 */
public class SimpleFactory {

    public Cat newCat() {
        return new Cat();
    }

    public Dog newDog() {
        return new Dog();
    }

    public static void main(String[] args) {

        SimpleFactory simpleFactory = new SimpleFactory();
        simpleFactory.newCat().move();
        simpleFactory.newDog().move();
    }
}
