package cn.aezo.designpattern.c04_abstract_factory;

/**
 * @author smalle
 * @date 2020-06-09 21:23
 */
public class Main {

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        String name = "ZhangsanFarmFactory";
        // String name = "LisiFarmFactory";
        Class c = Class.forName(Main.class.getName().replace(".Main", "." + name));

        FarmFactory farmFactory = (FarmFactory) c.newInstance();
        Farm farm1 = farmFactory.newAnimal();
        Farm farm2 = farmFactory.newPlant();
        farm1.grouth();
        farm2.grouth();
    }

}
