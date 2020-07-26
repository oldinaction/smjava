package cn.aezo.jvm.c05_gc;

/**
 * 测试对象分配在栈上和TLAB(Thread Local Allocation Buffer)上
 *
 * 1.正常执行(默认使用TLAB功能)耗时：603 (需要预热，即前几次不纳入到统计)
 * 2.增加以下JVM参数耗时：910
 * -XX:-DoEscapeAnalysis -XX:-EliminateAllocations -XX:-UseTLAB // 去掉逃逸分析、去掉标量替换、去掉TLAB分配
 *
 * @author smalle
 * @date 2020-07-05 17:56
 */
public class T01_TLAB {
    public static void main(String[] args) {
        T01_TLAB t = new T01_TLAB();

        long start = System.currentTimeMillis();
        for(int i=0; i<1000_0000; i++) t.alloc(i); // 循环1000w次
        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }

    void alloc(int i) {
        new User(i, "name " + i); // 此对象只在此方法使用过，因此没有逃逸
    }

    class User {
        int id;
        String name;

        public User(int id, String name) {
            this.id = id;
            this.name = name;
        }
    }
}
