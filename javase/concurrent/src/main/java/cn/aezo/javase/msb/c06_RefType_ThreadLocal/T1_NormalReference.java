package cn.aezo.javase.msb.c06_RefType_ThreadLocal;

import java.io.IOException;

/**
 * 强引用：又称普通引用，当每有强引用指向该对象时，该对象才会被垃圾回收
 *
 * @author smalle
 * @date 2020-06-06 09:58
 */
public class T1_NormalReference {
    public static void main(String[] args) throws IOException {
        Z z = new Z();
        // Z z = new Z(new byte[1024]); // 结果一样
        z = null;
        System.gc(); // 手动进行那就回收
        System.in.read(); // 阻塞线程，观察输出：不一会便打印finalize...
    }
}
