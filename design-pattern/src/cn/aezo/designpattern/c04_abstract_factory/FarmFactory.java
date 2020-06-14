package cn.aezo.designpattern.c04_abstract_factory;

/**
 * 农场工厂
 *
 * @author smalle
 * @date 2020-06-09 21:01
 */
public interface FarmFactory {
    Farm newAnimal();

    Farm newPlant();
}
