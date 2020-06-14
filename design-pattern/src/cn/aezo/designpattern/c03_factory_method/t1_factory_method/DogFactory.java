package cn.aezo.designpattern.c03_factory_method.t1_factory_method;

/**
 * @author smalle
 * @date 2020-06-09 20:40
 */
public class DogFactory implements AnimalFactory {
    @Override
    public Animal newAnimal() {
        return new Dog();
    }
}
