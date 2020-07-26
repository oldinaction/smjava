package cn.aezo.concurrent_msb.c06_RefType_ThreadLocal;

import java.io.IOException;
import java.lang.ref.SoftReference;

/**
 * 一个对象如果只被软引用对象指向时，当内存不足时(可指定IDEA的VM参数如-Xms20M -Xmx20M)才会回收该对象，否则不会回收。主要用在缓存
 *
 * [B@45c8e616
 * [B@45c8e616
 * null
 *
 * @author smalle
 * @date 2020-06-06 10:07
 */
public class T2_SoftReference {

    public static void main(String[] args) throws InterruptedException, IOException {
        // softReference为强引用，指向一个SoftReference对象，此对象中有一个软引用指向了byte数组对象
        SoftReference<byte[]> softReference = new SoftReference<>(new byte[1024*1024*10]); // 10M
        // SoftReference<Z> softReference = new SoftReference<>(new Z(new byte[1024*1024*10])); // 如果用一个对象包裹则会报OOM?

        // 内存充足时，下列方式都会让软引用回收
        System.out.println(softReference.get());
        // 空间充足，软引用指向的对象不会回收
        System.gc();
        Thread.sleep(1000);
        System.out.println(softReference.get());
        // softReference = null; // 此方式也不会让软引用回收
        // System.in.read();

        // 设置JVM参数-Xms20M -Xmx20M，此时内存空间不足，则会回收掉软引用指向的对象
        byte[] b = new byte[1024*1024*15];
        System.out.println(softReference.get());
        System.in.read();
    }
}
