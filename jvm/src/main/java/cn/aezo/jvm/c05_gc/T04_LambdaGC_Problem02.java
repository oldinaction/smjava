package cn.aezo.jvm.c05_gc;

/**
 * lambda表达式导致方法区溢出问题。`-XX:MaxMetaspaceSize=10M -XX:+PrintGCDetails` 设置方法区大小进行测试
 *
 * @author smalle
 * @date 2020-07-06 07:53
 */
public class T04_LambdaGC_Problem02 {
    public static void main(String[] args) {
        for(;;) {
            I i = C::n;
        }
    }

    public static interface I {
        void m();
    }

    public static class C {
        static void n() {
            System.out.println("hello");
        }
    }
}
