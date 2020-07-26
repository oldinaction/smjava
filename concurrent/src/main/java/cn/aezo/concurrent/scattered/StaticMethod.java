package cn.aezo.concurrent.scattered;

import cn.aezo.concurrent.tools.AbstractMultiThreadTestTemplate;

import java.util.Date;

/**
 * 结论：可以并发访问static修饰的方法
 */
public class StaticMethod {

    public static void testStaticMethod() {
        long id = Thread.currentThread().getId();
        System.out.println(new Date() + " Thread Start: " + id); // 可以并发访问static修饰的方法 (此行代码可能同时执行)
        try {
            // 逻辑处理
            Thread.sleep(3000); // 所有的线程执行到此处后，进行等待
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(new Date() + " Thread End: " + id);
    }

    static class DemoTest extends AbstractMultiThreadTestTemplate {
        public static void main(String[] args) {
            new DemoTest().run(100);
        }

        @Override
        public void beforeExec() {}

        @Override
        public void exec() {
            testStaticMethod();
        }

        @Override
        public void afterExec() {}
    }
}


