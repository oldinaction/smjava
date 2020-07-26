package cn.aezo.concurrent_msb.c02_1_volatile;

/**
 * volatile可以保证线程间可见性
 *
 * @author smalle
 * @date 2020-05-31 15:07
 */
public class T01_volatile {
    private volatile static boolean running = true; // 加volatile, 子线程会立马停止
    // private static boolean running = true; // 不加则子线程不会立马停止

    public static void main(String[] args) throws InterruptedException {

        new Thread(() -> {
            System.out.println("start...");
            while (running) {
                // System.out.println(1); // 如果此处有任何语句则很难观察到和没有volatile的区别
            }
            System.out.println("end...");
        }).start();

        Thread.sleep(1000);

        running = false;
    }

}
