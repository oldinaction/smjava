package cn.aezo.javase.msb.c05_4_Map;

import java.util.HashMap;
import java.util.IdentityHashMap;

/**
 * 结果：
 *
 * 1
 * 3
 * 2
 * null
 * 3
 *
 * @author smalle
 * @date 2020-06-05 08:15
 */
public class T3_IdentityHashMap {

    public static void main(String[] args) {
        HashMap hashMap = new HashMap(); // put时，key值是否相同是基于key的hashcode值来的
        hashMap.put(new String("a"), 1);
        hashMap.put(new String("a"), 2);
        System.out.println(hashMap.size()); // 1, 由于String重写了hashcode方法，从而上述两个key的hashcode值相同

        hashMap.put(new People("a"), 1);
        hashMap.put(new People("a"), 2);
        System.out.println(hashMap.size()); // 3，People没有重写hashcode方法，得到两个key

        IdentityHashMap identityHashMap = new IdentityHashMap();
        identityHashMap.put(new String("a"), 1);
        identityHashMap.put(new String("a"), 2);
        System.out.println(identityHashMap.size()); // 2，基于地址来的判断key值是否相同的(==判断的是地址，equals判断的是hashcode)
        System.out.println(identityHashMap.get("a")); // null

        String key = new String("a");
        identityHashMap.put(key, 3);
        System.out.println(identityHashMap.get(key)); // 3
    }

    static class People {
        private String name;

        public People(String name) {
            this.name = name;
        }
    }

}
