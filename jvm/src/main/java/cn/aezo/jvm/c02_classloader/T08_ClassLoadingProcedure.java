package cn.aezo.jvm.c02_classloader;

/**
 * @author smalle
 * @date 2020-07-02 22:05
 */
public class T08_ClassLoadingProcedure {
    public static void main(String[] args) {
        System.out.println(T1.count); // 2
        System.out.println(T2.count); // 3
    }
}

class T1 {
    // 1.类加载时
    // Preparation阶段t=null, count=0
    // Initializing阶段：(1)实例化T1 -> count=1 -> t=xxx (2) count=2
    // 2.类加载完成，执行T1.count，返回2
    public static T1 t = new T1();
    public static int count = 2;

    private T1() {
        count ++;
    }
}

class T2 {
    // 和上面不同的是这两行位置变化了
    // Preparation阶段count=0, t=null
    // Initializing阶段：(1)count=2 (2) 实例化T1 -> count=3 -> t=xxx
    public static int count = 2;
    public static T2 t = new T2();

    private T2() {
        count ++;
    }
}