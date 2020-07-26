package cn.aezo.concurrent_msb.c05_3_Queue;

import java.util.ArrayDeque;

/**
 * Deque双端队列，ArrayDeque线程不安全，LinkedBlockingDeque为线程安全
 *
 * @author smalle
 * @date 2020-06-04 09:17
 */
public class T5_Deque_ArrayDeque {

    public static void main(String[] args) {
        list();
        System.out.println();

        stack();
        System.out.println();

        thread();
    }

    /**
     * 作为队列使用
     */
    public static void list() {
        ArrayDeque<Integer> arrayDeque = new ArrayDeque<>();

        arrayDeque.addLast(1);
        arrayDeque.addLast(3);
        arrayDeque.addLast(2);

        System.out.println(arrayDeque.getFirst()); // 1
        System.out.println(arrayDeque.getLast()); // 2
    }

    /**
     * 作为栈使用
     */
    public static void stack() {
        ArrayDeque<Integer> arrayDeque = new ArrayDeque<>();

        arrayDeque.addFirst(1);
        arrayDeque.addFirst(3);
        arrayDeque.addFirst(2);

        System.out.println(arrayDeque.getFirst()); // 2
        System.out.println(arrayDeque.getLast()); // 1
    }

    public static void thread() {
        ArrayDeque<Integer> arrayDeque = new ArrayDeque<>();

        Thread[] threads = new Thread[10];
        for (int i = 0; i < 10; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    arrayDeque.offer(j); // 线程不安全，此处还可能会报错
                }
            });
        }

        for (Thread thread : threads) thread.start();
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println(arrayDeque.size()); // 如：1416，小于10000
    }
}
