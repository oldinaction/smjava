package cn.aezo.javase.msb.c06_RefType_ThreadLocal;

/**
 * 结果：
 *
 * t1 str1: t1-1
 * t1 str2: t1-2
 * main str: null
 *
 * @author smalle
 * @date 2020-06-05 22:03
 */
public class T5_ThreadLocal {

    public static void main(String[] args) {
        ThreadLocal<String> threadLocal1 = new ThreadLocal<>();
        ThreadLocal<String> threadLocal2 = new ThreadLocal<>();

        new Thread(() -> {
            threadLocal1.set(Thread.currentThread().getName() + "-1");
            threadLocal2.set(Thread.currentThread().getName() + "-2");

            String str1 = threadLocal1.get();
            System.out.println("t1 str1: "+ str1);

            String str2 = threadLocal2.get();
            System.out.println("t1 str2: "+ str2);
        }, "t1").start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        String str = threadLocal1.get();
        System.out.println("main str: "+ str);
    }
}
