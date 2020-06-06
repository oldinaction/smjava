package cn.aezo.javase.msb.c05_3_Queue;

import java.util.concurrent.PriorityBlockingQueue;

/**
 * @author smalle
 * @date 2020-06-04 07:50
 */
public class T2_PriorityBlockingQueue {

    /**
     * 结果：基于优先级(内部有一个排序器)。put入队不阻塞(调用offer)，take出队阻塞
     *
     * [1, 3, 2]
     * 1
     * 2
     * 3
     *
     * @param args
     */
    public static void main(String[] args) {
        PriorityBlockingQueue<Integer> queue = new PriorityBlockingQueue<>(2);

        queue.put(1);
        queue.put(3);
        queue.put(2);
        System.out.println(queue);

        try {
            for (int i = 0; i < 3; i++) {
                Integer num = queue.take();
                System.out.println(num);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}
