package cn.aezo.jvm.c02_classloader;

/**
 * 1.默认(-Xmixed混合模式)执行大概时间 6784
 * 2.设置JVM参数-Xint(解释执行)大概时间 1分钟还没执行完
 * 3.使用JVM参数-Xcomp(编译执行)大概时间 4271
 *
 * @author smalle
 * @date 2020-07-02 20:53
 */
public class T07_WayToRun {
    public static void main(String[] args) {
        // 此时_只是为了看的清楚，值还是100000
        for(int i=0; i<10_0000; i++)
            m();

        long start = System.currentTimeMillis();
        for(int i=0; i<10_0000; i++) {
            m();
        }
        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }

    public static void m() {
        for(long i=0; i<10_0000L; i++) {
            long j = i%3;
        }
    }
}
