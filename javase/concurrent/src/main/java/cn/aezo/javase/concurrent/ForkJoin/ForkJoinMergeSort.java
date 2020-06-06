package cn.aezo.javase.concurrent.ForkJoin;

import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

/**
 * 基于Fork/Join框架实现归并排序
 * @author smalle
 * @date 2019-07-12 11:06
 */
public class ForkJoinMergeSort {
    /**
     * [for/join mode]Total cost: 18.409 s, for 200000000 items' sort work.
     * [normal mode]Total cost: 22.011 s, for 200000000 items' sort work.
     */
    public static void main(String ... args) {
        new Worker().runWork();
    }
}

class Worker {

    private static final boolean isDebug = false;

    public void runWork() {
        // mock the data
        int[] array = mockArray(200000000, 1000000);

        // Fork/Join 机制
        forkJoinCase(array);

        // 普通机制
        normalCase(array);

    }

    private void printArray(int[] arr) {

        if (isDebug == false) {
            return;
        }

        for (int i = 0; i < arr.length; i ++) {
            System.out.print(arr[i] + " ");
        }

        System.out.println();
    }

    private void forkJoinCase(int[] array) {
        ForkJoinPool pool = new ForkJoinPool();
        // 使用10个线程计算，默认取计算机核心数
        // [for/join mode]Total cost: 12.238 s, for 200000000 items' sort work.
        // ForkJoinPool pool = new ForkJoinPool(10);

        MergeSortTask mergeSortTask = new MergeSortTask(array, 0, array.length - 1);

        long start = System.currentTimeMillis();

        pool.invoke(mergeSortTask);

        long end = System.currentTimeMillis();

        printArray(array);

        System.out.println("[for/join mode]Total cost: " + (end - start) / 1000.0 + " s, for " +
                array.length + " items' sort work.");
    }

    private void normalCase(int[] array) {

        long start = System.currentTimeMillis();

        new MergeSortWorker().sort(array, 0, array.length - 1);

        long end = System.currentTimeMillis();

        printArray(array);

        System.out.println("[normal mode]Total cost: " + (end - start) / 1000.0 + " s, for " +
                array.length + " items' sort work.");
    }

    private static final  int[] mockArray(int length, int up) {
        if (length <= 0) {
            return null;
        }

        int[] array = new int[length];

        Random random = new Random(47);

        for (int i = 0; i < length; i ++) {
            array[i] = random.nextInt(up);
        }

        return array;
    }
}

class MergeSortTask extends RecursiveAction {

    private static final int threshold = 100000;
    private final MergeSortWorker mergeSortWorker = new MergeSortWorker();

    private int[] data;

    private int left;
    private int right;

    public MergeSortTask(int[] array, int l, int r) {
        this.data = array;
        this.left = l;
        this.right = r;
    }

    @Override
    protected void compute() {
        if (right - left < threshold) {
            // 数据较小直接计算，不使用工作窃取算法
            mergeSortWorker.sort(data, left, right);
        } else {
            // 数据较多，使用工作窃取算法
            int mid = left + (right - left) / 2;
            // 对任务进行分割；当运行MergeSortTask时，会递归分割
            MergeSortTask l = new MergeSortTask(data, left, mid);
            MergeSortTask r = new MergeSortTask(data, mid + 1, right);

            invokeAll(l, r);

            // 合并分割结果
            mergeSortWorker.merge(data, left, mid, right);
        }
    }
}

class MergeSortWorker {

    // Merges two subarrays of arr[].
    // First subarray is arr[l..m]
    // Second subarray is arr[m+1..r]
    void merge(int arr[], int l, int m, int r) {
        // Find sizes of two subarrays to be merged
        int n1 = m - l + 1;
        int n2 = r - m;

        /* Create temp arrays */
        int L[] = new int[n1];
        int R[] = new int[n2];

        /*Copy data to temp arrays*/
        for (int i = 0; i < n1; ++i)
            L[i] = arr[l + i];
        for (int j = 0; j < n2; ++j)
            R[j] = arr[m + 1 + j];

        /* Merge the temp arrays */

        // Initial indexes of first and second subarrays
        int i = 0, j = 0;

        // Initial index of merged subarry array
        int k = l;
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                arr[k ++] = L[i ++];
            } else {
                arr[k ++] = R[j ++];
            }
        }

        /* Copy remaining elements of L[] if any */
        while (i < n1) {
            arr[k ++] = L[i ++];
        }

        /* Copy remaining elements of R[] if any */
        while (j < n2) {
            arr[k ++] = R[j ++];
        }
    }

    // Main function that sorts arr[l..r] using
    // merge()
    void sort(int arr[], int l, int r) {
        if (l < r) {
            // Find the middle point
            int m = l + (r - l) / 2;

            // Sort first and second halves
            sort(arr, l, m);
            sort(arr, m + 1, r);

            // Merge the sorted halves
            merge(arr, l, m, r);
        }
    }
}

