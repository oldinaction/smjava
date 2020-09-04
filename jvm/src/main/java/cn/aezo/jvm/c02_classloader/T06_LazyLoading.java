package cn.aezo.jvm.c02_classloader;

/**
 * 严格讲应该叫Lazy Initializing，因为java虚拟机规范并没有严格规定什么时候必须loading,但严格规定了什么时候Initializing
 *
 * @author smalle
 * @date 2020-07-02 18:17
 */
public class T06_LazyLoading {
    /**
     * 每一行分开打印可观察类初始化的时机
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        // P p; // 无打印 ==> 未初始化P
        // X x = new X(); // 打印P ==> 初始化了P
        // System.out.println(P.i); // 打印8 ==> 未初始化
        // System.out.println(P.j); // 打印P、9 ==> 初始化了
        // Class.forName("cn.aezo.jvm.c02_classloader.T06_LazyInitializing$P"); // 打印P ==> 初始化了

    }

    public static class P {
        final static int i = 8;
        static int j = 9;
        static {
            // 执行Initializing时，会执行static块
            System.out.println("P");
        }
    }

    public static class X extends P {
        static {
            System.out.println("X");
        }
    }
}

