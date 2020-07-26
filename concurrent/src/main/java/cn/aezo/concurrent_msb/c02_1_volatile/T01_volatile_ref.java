package cn.aezo.concurrent_msb.c02_1_volatile;

/**
 * volatile只能观测到简单数据类型和引用的变化，如果引用指向的对象属性值(包括数组)变化了是监测不到的
 *
 * @author smalle
 * @date 2020-05-31 15:41
 */
public class T01_volatile_ref {

    private boolean running = true;

    volatile static T01_volatile_ref ref = new T01_volatile_ref();

    private void run () {
        System.out.println("start...");
        while (running) {

        }
        System.out.println("end...");
    }

    public static void main(String[] args) throws InterruptedException {

        new Thread(ref::run).start();

        // 子程序不停止
        Thread.sleep(1000);
        ref.running = false;

        // 子程序也不停止, why?
        Thread.sleep(3000);
        T01_volatile_ref ref1 = new T01_volatile_ref();
        ref1.running = false;
        ref = ref1;
    }


}
