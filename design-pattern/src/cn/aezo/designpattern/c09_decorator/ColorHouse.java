package cn.aezo.designpattern.c09_decorator;

/**
 * @author smalle
 * @date 2020-06-13 08:49
 */
public class ColorHouse implements House {
    private House house;

    public ColorHouse(House house) {
        this.house = house;
    }

    @Override
    public void build() {
        this.house.build();
        System.out.println("房子墙面刷白完成");
    }
}
