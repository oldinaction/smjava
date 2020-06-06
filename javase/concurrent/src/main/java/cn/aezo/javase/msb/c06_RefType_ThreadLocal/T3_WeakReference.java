package cn.aezo.javase.msb.c06_RefType_ThreadLocal;

import java.lang.ref.WeakReference;

/**
 *
 * 结果：
 *
 * cn.aezo.javase.msb.c06_RefType_ThreadLocal.Z@4cdbe50f
 * null
 * finalize...
 *
 * @author smalle
 * @date 2020-06-06 13:14
 */
public class T3_WeakReference {

    public static void main(String[] args) {
        WeakReference<Z> weakReference = new WeakReference<>(new Z(new byte[1024]));
        System.out.println(weakReference.get());
        System.gc();
        System.out.println(weakReference.get());
    }
}
