package cn.aezo.designpattern.c11_flyweight;

import java.util.Random;

/**
 * 结果如：
 *
 * create Circle, color:black
 * color:black
 * create Circle, color:red
 * color:red
 * color:black
 * create Circle, color:blue
 * color:blue
 * create Circle, color:white
 * color:white
 * color:white
 * color:white
 * color:blue
 * color:black
 * color:red
 *
 * @author smalle
 * @date 2020-06-13 12:39
 */
public class Main {

    public static void main(String[] args) {
        String[] colors = new String[]{"red", "blue", "yellow", "black", "white"};
        Random random = new Random();

        for (int i = 0; i < 10; i++) {
            int index = random.nextInt(5);
            Shape shape = ShapeFactory.getShape(colors[index]);
            shape.draw();
        }
    }

}
