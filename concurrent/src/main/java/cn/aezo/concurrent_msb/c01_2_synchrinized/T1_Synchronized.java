package cn.aezo.concurrent_msb.c01_2_synchrinized;

/**
 * synchronized 加锁方式
 * @author smalle
 * @date 2020-01-11 21:41
 */
public class T1_Synchronized {

    private int count = 10;
    private static int count2 = 10;
    private Object o = new Object();

    // 访问这个方法的时候需要上锁，count++为线程不安全。此时为线程不安全
    public void m0() {
        count++;
    }

    // 锁定对象方式1
    public void m1() {
        // 任何线程要执行下面的代码，必须先拿到this的锁
        synchronized(this) {
            count++;
            System.out.println(Thread.currentThread().getName() + " count = " + count);
        }
    }

    // 锁定对象方式2
    // 等同于在方法的代码执行时要synchronized(this)
    public synchronized void m2() {
        count++;
        System.out.println(Thread.currentThread().getName() + " count = " + count);
    }

    // 锁定对象方式3
    public void m3() {
        // 任何线程要执行下面的代码，必须先拿到o的锁
        synchronized(o) {
            count--;
            System.out.println(Thread.currentThread().getName() + " count = " + count);
        }
    }

    // 锁定类方式1
    public static void n1() {
        // 每个类加载到内存里面都会产生一个class对象，此对象与该类的代码相对应，此处锁的就是该class对象
        synchronized(T1_Synchronized.class) { // 此处不能使用synchronized(this)，会语法报错
            count2--;
            System.out.println(Thread.currentThread().getName() + " count2 = " + count2);
        }
    }

    // 锁定类方式2
    // 这里等同于synchronized(T05_Synchronized1.class)
    public synchronized static void n2() {
        count2--;
        System.out.println(Thread.currentThread().getName() + " count2 = " + count2);
    }


}
