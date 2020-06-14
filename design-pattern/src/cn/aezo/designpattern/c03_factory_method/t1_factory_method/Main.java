package cn.aezo.designpattern.c03_factory_method.t1_factory_method;

/**
 * 工厂方法
 *
 *
 *
 * @author smalle
 * @date 2020-06-09 20:42
 */
public class Main {

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        /**
         * 可以方便的修改需要创建的产品
         */
        String name = "DogFactory";
        // String name = "CatFactory";
        Class c = Class.forName(Main.class.getName().replace(".Main", "." + name));

        AnimalFactory animalFactory = (AnimalFactory) c.newInstance();
        Animal animal = animalFactory.newAnimal();
        animal.move();
    }
}
