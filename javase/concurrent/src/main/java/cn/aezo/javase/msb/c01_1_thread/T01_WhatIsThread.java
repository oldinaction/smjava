package cn.aezo.javase.msb.c01_1_thread;

import java.util.concurrent.TimeUnit;

/**
 * @author smalle
 * @date 2020-01-11 18:24
 * 结果：T1和main无规律交叉打印
 */
public class T01_WhatIsThread {

    public static void main(String[] args) {
        T1 t1 = new T1();
        t1.start();

        for(int i=0; i<10; i++) {
            try {
                TimeUnit.MICROSECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("main");
        }
    }

    private static class T1 extends Thread {
        @Override
        public void run() {
            for(int i=0; i<10; i++) {
                try {
                    TimeUnit.MICROSECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("T1");
            }
        }
    }
}
