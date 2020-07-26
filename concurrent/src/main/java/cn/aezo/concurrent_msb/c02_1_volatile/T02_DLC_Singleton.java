package cn.aezo.concurrent_msb.c02_1_volatile;

/**
 * DLC(Double Check Lock)单例
 *
 * @author smalle
 * @date 2020-05-31 17:18
 */
public class T02_DLC_Singleton {
    private static volatile T02_DLC_Singleton INSTANCE;

    private T02_DLC_Singleton() {
    }

    public static T02_DLC_Singleton getInstance() {
        // do something...
        if (INSTANCE == null) {
            // synchronized不锁定在方法上是为了减少锁定代码量
            synchronized (T02_DLC_Singleton.class) {
                // 双重检查。如果不进行双重检查，有可能出现两个线程同时进行第一次判断发现INSTANCE为空，进入到synchronized，此时会先后执行两次实例初始化
                if(INSTANCE == null) {
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    /*
                     * Object o = new Object(); 可分为4步(此处同理)
                     * 1.new #11 <java/lang/Object>：申请内存(并设置默认值，如设置此o对象的某属性为int a = 0)
                     * 2.dup
                     * 3.invokespecial #1 <java/lang/Object.<init>>：实例化对象(设置属性的初始值，a = 1)
                     * 4.astore_1：将此对象的引用赋值给变量o
                     *
                     *
                     * 如果不加volatile则可能出现指令重排，可能出现1-2-4-3的执行顺序(就是将没有初始化完全的对象引用提前赋值给了变量)
                     * 如果第一个线程执行按照此方式执行到第4(还未执行3)，第二个线程判断发现INSTANCE不为空(已经被赋值了引用地址)
                     * 则第二个线程可能会使用第一个线程创建的对象，此时可能使用到对象中的一些未初始化好的属性产生意想不到的结果
                     */
                    INSTANCE = new T02_DLC_Singleton();
                }
            }
        }
        return INSTANCE;
    }

    public static void main(String[] args) {
        for(int i=0; i<100; i++) {
            new Thread(()->{
                System.out.println(T02_DLC_Singleton.getInstance().hashCode());
            }).start();
        }
    }
}
