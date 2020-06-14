package cn.aezo.designpattern.c09_decorator;

/**
 * @author smalle
 * @date 2020-06-13 08:50
 */
public class GradenHouse implements House {
    private House house;

    public GradenHouse(House house) {
        this.house = house;
    }

    @Override
    public void build() {
        this.house.build();
        System.out.println("房子周围花园建造完成");
    }
}
