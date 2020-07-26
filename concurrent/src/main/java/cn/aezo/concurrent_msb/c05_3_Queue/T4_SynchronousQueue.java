package cn.aezo.concurrent_msb.c05_3_Queue;

import java.util.concurrent.SynchronousQueue;

/**
 * 当调用put放入元素后，如果没有被取走(take)，则put后会一致等待直到take拿走元素
 *
 * 结果：每隔一秒打印一个
 *
 * 1
 * 3
 * null
 * 2
 *
 * @author smalle
 * @date 2020-06-04 09:01
 */
public class T4_SynchronousQueue {

    public static void main(String[] args) {
        SynchronousQueue<Integer> queue = new SynchronousQueue<>();

        new Thread(() -> {
            try {
                queue.put(1);
                queue.put(3);
                queue.put(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        try {
            Thread.sleep(1000);
            System.out.println(queue.take());

            Thread.sleep(1000);
            System.out.println(queue.poll());

            Thread.sleep(1000);
            System.out.println(queue.peek()); // peek并未被具体实现，直接返回null

            Thread.sleep(1000);
            System.out.println(queue.take());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
