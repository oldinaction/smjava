package cn.aezo.javase.msb.c01_1_thread;

/**
 * @author smalle
 * @date 2020-01-11 18:30
 */
public class T02_HowToCreateThread {

    public static void main(String[] args) {
        // 1.继承Thread类
        new MyThread().start();

        // 2.实现Runnable接口
        new Thread(new MyRunnable()).start();

        // 第二种方式的变形，jdk1.8增加的Lambda语法
        new Thread(()->{
            System.out.println("Hello Lambda!");
        }).start();

        // 通过线程池，如：Executors.newCachedThread，本质还是上述2种方式
    }

    static class MyThread extends Thread {
        @Override
        public void run() {
            System.out.println("Hello MyThread!");
        }
    }

    static class MyRunnable implements Runnable {
        @Override
        public void run() {
            System.out.println("Hello MyRunnable!");
        }
    }
}
