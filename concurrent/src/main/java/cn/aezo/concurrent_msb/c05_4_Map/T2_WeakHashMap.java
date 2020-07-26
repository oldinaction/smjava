package cn.aezo.concurrent_msb.c05_4_Map;

import java.util.Iterator;
import java.util.Map;
import java.util.WeakHashMap;

/**
 * 结果：
 *
 * null
 * w0
 * weakHashMap: {three=w3, one=w1, two=w2}
 * contains key two: true
 * contains key five: false
 * contains value w1: true
 * weakHashMap: {one=w1, two=w2}
 * next: two - w2
 * after gc weakHashMap: {two=w2}
 *
 * @author smalle
 * @date 2020-06-04 21:30
 */
public class T2_WeakHashMap {

    public static void main(String[] args) {
        WeakHashMap weakHashMap = new WeakHashMap();

        String w1 = new String("one");
        String w2 = new String("two");
        String w3 = new String("three");

        System.out.println(weakHashMap.put(w1, "w0")); // 返回原始值 null
        System.out.println(weakHashMap.put(w1, "w1")); // 返回原始值 w0，w1的hashcode相同会覆盖之前的
        weakHashMap.put(w2, "w2");
        weakHashMap.put(w3, "w3");

        // 打印出wmap
        System.out.printf("weakHashMap: %s\n", weakHashMap);

        // containsKey(Object key) :是否包含键key
        System.out.printf("contains key two: %s\n", weakHashMap.containsKey("two"));
        System.out.printf("contains key five: %s\n", weakHashMap.containsKey("five"));

        // containsValue(Object value): 是否包含值value
        System.out.printf("contains value w1: %s\n", weakHashMap.containsValue("w1"));

        // remove(Object key) ： 删除键key对应的键值对
        weakHashMap.remove("three");

        System.out.printf("weakHashMap: %s\n", weakHashMap);

        // ---- 测试 WeakHashMap 的自动回收特性 ----

        // 将w1设置null，这意味着"弱键"w1再没有被其它对象引用，调用gc时会回收WeakHashMap中与"w1"对应的键值对
        w1 = null;
        // 内存回收。这里，会回收WeakHashMap中与"w1"对应的键值对
        System.gc();

        // 遍历WeakHashMap
        Iterator iter = weakHashMap.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry en = (Map.Entry) iter.next();
            System.out.printf("next: %s - %s\n", en.getKey(), en.getValue());
        }
        // 打印WeakHashMap的实际大小
        System.out.printf("after gc weakHashMap: %s\n", weakHashMap);
    }

}
