package cn.aezo.jvm.c04_instruction_set;

import java.util.ArrayList;
import java.util.List;

/**
 * jclasslib观察指令如下：
 *
 *   0 new #2 <cn/aezo/jvm/c04_instruction_set/T02_Object>
 *   3 dup
 *   4 invokespecial #3 <cn/aezo/jvm/c04_instruction_set/T02_Object.<init>>
 *   7 astore_1
 *   8 invokestatic #4 <cn/aezo/jvm/c04_instruction_set/T02_Object.m>
 *  11 aload_1
 *  12 invokespecial #5 <cn/aezo/jvm/c04_instruction_set/T02_Object.n1>
 *  15 aload_1
 *  16 invokevirtual #6 <cn/aezo/jvm/c04_instruction_set/T02_Object.n2>
 *  19 new #7 <java/util/ArrayList>
 *  22 dup
 *  23 invokespecial #8 <java/util/ArrayList.<init>>
 *  26 astore_2
 *  27 aload_2
 *  28 ldc #9 <hello>
 *  30 invokeinterface #10 <java/util/List.add> count 2
 *  35 pop
 *  36 new #7 <java/util/ArrayList>
 *  39 dup
 *  40 invokespecial #8 <java/util/ArrayList.<init>>
 *  43 astore_3
 *  44 aload_3
 *  45 ldc #11 <hello2>
 *  47 invokevirtual #12 <java/util/ArrayList.add>
 *  50 pop
 *  51 invokedynamic #13 <m, BootstrapMethods #0>
 *  56 astore 4
 *  58 invokedynamic #13 <m, BootstrapMethods #0>
 *  63 astore 5
 *  65 invokedynamic #14 <m, BootstrapMethods #1>
 *  70 astore 6
 *  72 getstatic #15 <java/lang/System.out>
 *  75 aload 4
 *  77 invokevirtual #16 <java/lang/Object.getClass>
 *  80 invokevirtual #17 <java/io/PrintStream.println>
 *  83 getstatic #15 <java/lang/System.out>
 *  86 aload 5
 *  88 invokevirtual #16 <java/lang/Object.getClass>
 *  91 invokevirtual #17 <java/io/PrintStream.println>
 *  94 getstatic #15 <java/lang/System.out>
 *  97 aload 6
 *  99 invokevirtual #16 <java/lang/Object.getClass>
 * 102 invokevirtual #17 <java/io/PrintStream.println>
 * 105 return
 *
 * @author smalle
 * @date 2020-07-04 20:56
 */
public class T02_Object {

    public static void main(String[] args) {
        T02_Object object = new T02_Object();

        // invokestatic
        m();

        // invokespecial
        object.n1();

        // invokevirtual
        object.n2();

        // invokeinterface
        List<String> list = new ArrayList<>();
        list.add("hello");
        // invokevirtual
        ArrayList<String> list2 = new ArrayList<>();
        list2.add("hello2");

        // invokedynamic
        I i1 = C::n;
        I i2 = C::n;
        I i3 = () -> {
            C.n();
        };
        /**
         * 打印结果如下，说明每个Lambda表达式会动态生成不同的Class
         *
         * class cn.aezo.jvm.c04_instruction_set.T02_Object$$Lambda$1/1747585824
         * class cn.aezo.jvm.c04_instruction_set.T02_Object$$Lambda$2/1149319664
         * class cn.aezo.jvm.c04_instruction_set.T02_Object$$Lambda$3/2093631819
         */
        System.out.println(i1.getClass());
        System.out.println(i2.getClass());
        System.out.println(i3.getClass());
    }

    private static void m() {}

    private void n1() {}

    public void n2() {}

    @FunctionalInterface
    public interface I {
        void m();
    }

    public static class C {
        static void n() {
            System.out.println("hello");
        }
    }
}
