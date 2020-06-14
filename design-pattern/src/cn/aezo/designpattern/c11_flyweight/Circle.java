package cn.aezo.designpattern.c11_flyweight;

/**
 * @author smalle
 * @date 2020-06-13 12:36
 */
public class Circle implements Shape {
    private String color;

    public Circle(String color){
        this.color = color;
    }

    @Override
    public void draw() {
        System.out.println("color:" + color);
    }
}
