package cn.aezo.designpattern.c04_abstract_factory;

/**
 * @author smalle
 * @date 2020-06-09 21:03
 */
public class ZhangsanFarmFactory implements FarmFactory {

    @Override
    public Farm newAnimal() {
        return new Cat();
    }

    @Override
    public Farm newPlant() {
        return new Cabbage();
    }
}
