package cn.aezo.jvm.c05_gc;

import java.util.LinkedList;
import java.util.List;

/**
 * 1. -XX:+PrintCommandLineFlags 打印启动程序时的JVM参数。结果如下：
 *      -XX:InitialHeapSize=266743424 -XX:MaxHeapSize=4267894784 -XX:+PrintCommandLineFlags -XX:+UseCompressedClassPointers -XX:+UseCompressedOops -XX:-UseLargePagesIndividualAllocation -XX:+UseParallelGC
 *      HelloGC!
 *      Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
 * 2. -XX:+UseConcMarkSweepGC -XX:+PrintCommandLineFlags -XX:+PrintGC 使用CMS垃圾回收器；PrintGC打印GC日志(PrintGCDetails打印详细、PrintGCTimeStamps打印产生时间、PrintGCCause打印GC产生原因)
 * 3. -Xmn10M -Xms40M -Xmx60M -XX:+PrintCommandLineFlags -XX:+PrintGCDetails 设置新生代大小为10M；老年代最小为40M，最大为60M
 * 4. -Xmn10M -Xms40M -Xmx60M -XX:+UseConcurrentMarkSweepGC -XX:+PrintGCDetails
 * 5. -Xmn10M -Xms40M -Xmx60M -XX:+UseG1GC -XX:+PrintGCDetails
 *
 * @author smalle
 * @date 2020-07-05 19:12
 */
public class T02_HelloGC {
    public static void main(String[] args) {
        System.out.println("HelloGC!");
        List list = new LinkedList();
        for(;;) {
            byte[] b = new byte[1024*1024]; // 产生1M的数据
            list.add(b); // 有引用指向，b永远不是垃圾对象，最终肯达会出现GC并OOM
        }
    }
}
