package cn.aezo.designpattern.c11_flyweight;

import java.util.HashMap;

/**
 * @author smalle
 * @date 2020-06-13 12:37
 */
public class ShapeFactory {
    private static final HashMap<String, Shape> map = new HashMap<>();

    public static Shape getShape(String color) {
        Shape shape = map.get(color);
        if(shape == null) {
            shape = new Circle(color);
            System.out.println("create Circle, color:" + color);
            map.put(color, shape);
        }
        return shape;
    }
}
