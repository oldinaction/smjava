package cn.aezo.designpattern.c09_decorator;

import java.io.BufferedInputStream;
import java.io.FileInputStream;

/**
 * @author smalle
 * @date 2020-06-13 08:34
 */
public class InputStreamMain {

    public static void main(String[] args) {
        try {
            // 采用了装饰者的设计模式，即不改变原类文件和使用继承的前提下，动态地扩展一个对象的功能
            // 比如说这里的支持缓冲流，通过创建一个包装对象（这里的BufferedInputStream），也就是装饰包裹真实的对象
            BufferedInputStream bis = new BufferedInputStream(new FileInputStream("E:\\text.txt"));
            long start = System.currentTimeMillis( );
            byte byteData;
            while ((byteData = (byte) bis.read()) != -1) {
                System.out.print((char) byteData);
            }
            long end = System.currentTimeMillis( );
            long diff = end - start;
            System.out.println("读取的时间时间共：" +  diff);
            bis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
