package cn.aezo.javase.msb.c06_RefType_ThreadLocal;

import java.io.IOException;
import java.lang.ref.PhantomReference;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.util.ArrayList;
import java.util.List;

/**
 * 遇到GC时，虚引用肯定被回收。当虚引用被回收时，只会将此引用放入到相应队列，从而可监测队列来获取垃圾回收虚引用的通知
 * 主要管理堆外内存，如当虚引用被回收时，通过监控ReferenceQueue来获取通知，从而进行堆外内存清理。使用场景如底层在实现JVM时会使用，Netty也会使用
 *
 * 结果如(且会报OOM)：
 *
 * null
 * null
 * finalize...
 * null
 * 虚引用被回收了==>java.lang.ref.PhantomReference@1f0c2d77
 * null
 *
 * @author smalle
 * @date 2020-06-06 14:01
 */
public class T4_PhantomReference {

    static ReferenceQueue referenceQueue = new ReferenceQueue();
    static List<byte[]> list = new ArrayList<>();

    // 需要设置JVM参数如：-Xms10M -Xmx10M
    public static void main(String[] args) throws IOException {

        PhantomReference<Z> phantomReference = new PhantomReference<>(new Z(), referenceQueue);

        new Thread(() -> {
            while (true) {
                list.add(new byte[1024*1024]);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(phantomReference.get()); // 无法通过虚引用获取指向的对象的值
            }
        }).start();

        new Thread(() ->{
            while (true) {
                Reference reference = referenceQueue.poll();
                if(reference != null) {
                    System.out.println("虚引用被回收了==>" + reference);
                    break;
                }
            }
        }).start();

        System.in.read();
    }
}
