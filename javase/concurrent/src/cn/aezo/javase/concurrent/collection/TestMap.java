package cn.aezo.javase.concurrent.collection;

import cn.aezo.javase.concurrent.scattered.MultiThreadTestSimpleTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author smalle
 * @date 2019-07-23 15:41
 */
public class TestMap {

    /**
     * 测试结果：HashMap等普通集合为线程不安全，此处Map的大小总小于10000；而ConcurrentHashMap则为线程安全
     */
    static class Test1 extends MultiThreadTestSimpleTemplate {
        private Map<String, Object> hashMap = new HashMap<>();
        private Map<String, Object> concurrentHashMap = new ConcurrentHashMap<>();

        public static void main(String[] args) {
            new Test1().run(10000, 100);
        }

        @Override
        public void beforeExec() {

        }

        @Override
        public void exec() {
            hashMap.put(UUID.randomUUID().toString(), new Object());
            concurrentHashMap.put(UUID.randomUUID().toString(), new Object());
        }

        @Override
        public void afterExec() {
            System.out.println(hashMap.keySet().size());
            System.out.println(concurrentHashMap.keySet().size());
        }
    }

}
