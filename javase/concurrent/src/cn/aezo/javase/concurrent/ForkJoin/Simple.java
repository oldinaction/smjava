package cn.aezo.javase.concurrent.ForkJoin;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @author smalle
 * @date 2019-07-12 11:20
 */
public class Simple {
    public static void main(String ... args) throws ExecutionException, InterruptedException, TimeoutException {
        int[] array = {100,400,200,90,80,300,600,10,20,-10,30,2000,1000};

        // 默认取计算机核心数，也可自定义线程数
        ForkJoinPool pool = new ForkJoinPool();
        // 注意此处结束取的数组的最后一个下标值
        MaxNumberTask task = new MaxNumberTask(array, 0, array.length - 1);
        Future<Integer> future = pool.submit(task);

        // 注意：只有调用了`future.get()`才会阻塞主线程
        System.out.println("Result:" + future.get(1, TimeUnit.SECONDS));
        System.out.println("end...");
    }

    // RecursiveAction：用于没有返回结果的任务
    /// RecursiveTask ：用于有返回结果的任务
    private static class MaxNumberTask extends RecursiveTask<Integer> {
        // 当任务大小大于此值是才进行任务分割
        private static final int THRESHOLD = 5;

        // the data array
        private int[] array;

        private int index0 = 0;
        private int index1 = 0;

        public MaxNumberTask(int[] array, int index0, int index1) {
            this.array = array;
            this.index0 = index0;
            this.index1 = index1;
        }

        @Override
        protected Integer compute() {
            int max = Integer.MIN_VALUE;

            if ((index1 - index0) <= THRESHOLD) {
                for (int i = index0; i <= index1; i ++) {
                    // try {
                    //     Thread.sleep(1000);
                    // } catch (InterruptedException e) {
                    //     e.printStackTrace();
                    // }

                    max = Math.max(max, array[i]);
                }
            } else {
                // fork/join
                int mid = index0 + (index1 - index0) / 2;
                MaxNumberTask lMax = new MaxNumberTask(array, index0, mid);
                MaxNumberTask rMax = new MaxNumberTask(array, mid + 1, index1);

                // 执行任务
                lMax.fork();
                rMax.fork();

                // 等待子任务结束并得到子结果
                int lm = lMax.join();
                int rm = rMax.join();

                // 合并子结果
                max = Math.max(lm, rm);
            }

            return max;
        }
    }
}
