package cn.aezo.javase.msb.c05_3_Queue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * add 超过集合容量会报错：java.lang.IllegalStateException: Queue full
 * offer 添加，超过集合容量则不再放入，也不报错，线程不会阻塞，返回是否放入成功
 * remove 移除头部元素并返回此元素，如果没有则抛出异常java.util.NoSuchElementException
 * poll 移除头部元素并返回此元素，如果没有则返回null
 * element 获取头部元素，如果没有则抛出异常
 * peek 获取头部元素，如果没有则返回null
 *
 * put 添加元素。容器装满时，put元素会阻塞，直到元素被取出有空余空间才能继续添加
 * take 移除头部元素并返回此元素。容器无元素时，take会阻塞，直到有元素被添加到容器
 *
 * @author smalle
 * @date 2020-06-03 22:32
 */
public class T1_ArrayBlockingQueue {

    public static void main(String[] args) {
        // add();

        // offer();

        // remove();

        // poll();

        // element();

        // peek();

        // put_take();
    }

    /**
     * java.lang.IllegalStateException: Queue full
     */
    public static void add() {
        BlockingQueue<String> arrayBlockingQueue = new ArrayBlockingQueue<>(3);

        for (int j = 0; j < 4; j++) {
            arrayBlockingQueue.add(Thread.currentThread().getName() + "-" + j);
        }

        System.out.println(arrayBlockingQueue.size());
    }

    /**
     * true
     * true
     * true
     * false
     * 3
     */
    public static void offer() {
        BlockingQueue<String> arrayBlockingQueue = new ArrayBlockingQueue<>(3);

        for (int j = 0; j < 4; j++) {
            boolean offerResult = arrayBlockingQueue.offer(Thread.currentThread().getName() + "-" + j);
            System.out.println(offerResult);
        }

        System.out.println(arrayBlockingQueue.size());
    }

    /**
     * 移除并返回被移除的元素，当容器中无元素是则报错java.util.NoSuchElementException
     */
    public static void remove() {
        BlockingQueue<String> arrayBlockingQueue = new ArrayBlockingQueue<>(3);

        for (int j = 0; j < 3; j++) {
            arrayBlockingQueue.add(Thread.currentThread().getName() + "-" + j);
        }
        System.out.println(arrayBlockingQueue);

        for (int j = 0; j < 4; j++) {
            String s = arrayBlockingQueue.remove();
            System.out.println(s);
        }
        System.out.println(arrayBlockingQueue);
    }

    /**
     * 结果：
     *
     * [main-0, main-1, main-2]
     * main-0
     * main-1
     * main-2
     * null
     * []
     */
    public static void poll() {
        BlockingQueue<String> arrayBlockingQueue = new ArrayBlockingQueue<>(3);

        for (int j = 0; j < 3; j++) {
            arrayBlockingQueue.add(Thread.currentThread().getName() + "-" + j);
        }
        System.out.println(arrayBlockingQueue);

        for (int j = 0; j < 4; j++) {
            String s = arrayBlockingQueue.poll();
            System.out.println(s);
        }
        System.out.println(arrayBlockingQueue);
    }

    /**
     * 如果没有则报错java.util.NoSuchElementException
     */
    public static void element() {
        BlockingQueue<String> arrayBlockingQueue = new ArrayBlockingQueue<>(1);

        for (int j = 0; j < 1; j++) {
            arrayBlockingQueue.add(Thread.currentThread().getName() + "-" + j);
        }
        System.out.println(arrayBlockingQueue);

        for (int j = 0; j < 2; j++) {
            String s = arrayBlockingQueue.element();
            System.out.println(s);
        }
        System.out.println(arrayBlockingQueue);

        arrayBlockingQueue.poll();
        String s = arrayBlockingQueue.element();
        System.out.println(s);
    }

    /**
     * 结果：
     *
     * null
     * [main-0, main-1, main-2]
     * main-0
     * main-0
     * [main-0, main-1, main-2]
     */
    public static void peek() {
        BlockingQueue<String> arrayBlockingQueue = new ArrayBlockingQueue<>(3);
        System.out.println(arrayBlockingQueue.peek());

        for (int j = 0; j < 3; j++) {
            arrayBlockingQueue.add(Thread.currentThread().getName() + "-" + j);
        }
        System.out.println(arrayBlockingQueue);

        for (int j = 0; j < 2; j++) {
            String s = arrayBlockingQueue.peek();
            System.out.println(s);
        }
        System.out.println(arrayBlockingQueue);
    }

    /**
     *
     * 结果如：前3行一起打印，1秒后打印第4-6行，再过1秒后打印最后几行
     *
     * Thread-3 已放入队列
     * Thread-2 已放入队列
     * Thread-4 已放入队列
     * [Thread-4, Thread-3, Thread-2]
     * Thread-4 已从队列取出
     * Thread-1 已放入队列
     * [Thread-3, Thread-2, Thread-1]
     * Thread-3 已从队列取出
     * Thread-0 已放入队列
     * [Thread-2, Thread-1, Thread-0]
     */
    public static void put_take() {
        BlockingQueue<String> arrayBlockingQueue = new ArrayBlockingQueue<>(3);

        Thread[] threads = new Thread[5];
        for (int i = 0; i < 5; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < 1; j++) {
                    try {
                        arrayBlockingQueue.put(Thread.currentThread().getName()); // 超过3个则阻塞，直到元素被取出
                        System.out.println(Thread.currentThread().getName() + " 已放入队列");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }

        for (Thread thread : threads) thread.start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            System.out.println(arrayBlockingQueue);
            System.out.println(arrayBlockingQueue.take() + " 已从队列取出"); // 先进先出

            Thread.sleep(1000);

            System.out.println(arrayBlockingQueue);
            System.out.println(arrayBlockingQueue.take() + " 已从队列取出");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(arrayBlockingQueue);
    }

}
