package cn.aezo.concurrent_msb.c04_4_VarHandle;

import java.lang.invoke.MethodHandles;
import java.lang.invoke.VarHandle;

/**
 * VarHandle需要JDK9及以上
 *
 * 结果：
 *
 * 10
 * 11
 * 12
 * 15
 *
 * @author smalle
 * @date 2020-06-06 16:56
 */
public class T1_VarHandle {
    int x = 10;

    public static void main(String[] args) {
        T1_VarHandle t = new T1_VarHandle();

        VarHandle varHandle = null;
        try {
            // 获取T1_VarHandle.x的引用，此时相当于varHandle指向了x指向的内存
            varHandle = MethodHandles.lookup().findVarHandle(T1_VarHandle.class, "x", int.class);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }

        if(varHandle != null) {
            // 取值设置
            System.out.println(varHandle.get(t)); // 10
            varHandle.set(t, 11);
            System.out.println(t.x); // 11

            // 原子性操作
            varHandle.compareAndSet(t, 11, 12); // 原子性操作，期望原值为11，需要改成12
            System.out.println(t.x); // 12

            varHandle.getAndAdd(t, 3);
            System.out.println(t.x); // 15
        }
    }
}
