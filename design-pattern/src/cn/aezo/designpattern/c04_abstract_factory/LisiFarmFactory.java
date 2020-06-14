package cn.aezo.designpattern.c04_abstract_factory;

/**
 * @author smalle
 * @date 2020-06-09 21:23
 */
public class LisiFarmFactory implements FarmFactory {
    @Override
    public Farm newAnimal() {
        return new Dog();
    }

    @Override
    public Farm newPlant() {
        return new Potato();
    }
}
