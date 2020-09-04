package cn.aezo.algorithms.c01_sort;

/**
 *
 * 将数组一分二，递归使之左右两边的数字有序；然后准备left、right两个指针分别指向左右两边数组，比较小的拷贝到新数组，并移动小的指针
 *
 * @author smalle
 * @date 2020-08-23 16:07
 */
public class MergeSort {

    public static void main(String[] args) {
        int[] arr = {6, 1, 3, 7, 2, 8, 9, 5, 4};
        // int[] arr = {1, 2, 23, 6, 1,15, 3, 7, 2, 10, 8, 9, 14, 11, 22, 19, 5, 4};

        sort(arr, 0, arr.length - 1);

        for (int i = 0; i < arr.length; i++) {
            System.out.print((arr[i]) + " ");
        }
    }

    public static void sort(int[] arr, int start, int end) {
        if(start > end || end == start) {
            return;
        }
        int mid = start + (end - start) / 2;
        sort(arr, start, mid);
        sort(arr, mid + 1, end);
        merge(arr, start, mid, end);
    }

    public static void merge(int[] arr, int start, int mid, int end) {
        int[] help = new int[end - start + 1];
        int left = start;
        int right = mid + 1;
        int index = 0;

        while (left <= mid && right <= end) {
            help[index++] = arr[left] <= arr[right] ? arr[left++] : arr[right++];
        }

        // 左右两边必有一个越界
        while (left <= mid) {
            help[index++] = arr[left++];
        }
        while (right <= end) {
            help[index++] = arr[right++];
        }

        for (int i = 0; i < help.length; i++) {
            arr[start + i] = help[i];
        }
    }
}
