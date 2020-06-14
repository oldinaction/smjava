package cn.aezo.designpattern.c09_decorator;

/**
 * 结果：
 *
 * 房子框架搭建完成
 * 房子墙面刷白完成
 * 房子周围花园建造完成
 *
 * @author smalle
 * @date 2020-06-13 08:51
 */
public class Main {

    public static void main(String[] args) {
        House house = new GradenHouse(new ColorHouse(new BaseHouse()));
        house.build();
    }
}
