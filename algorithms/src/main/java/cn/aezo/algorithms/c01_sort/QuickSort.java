package cn.aezo.algorithms.c01_sort;

/**
 * 快速排序(填坑法)
 *
 * 使用left、right左右指针分别指向数组两端，交替一个指向坑位，一个指向被比较元素，直到两个元素重合，则认为排好第一个元素
 *
 * 将right向左移动，如果指向元素比left缓存元素大，则保持不变；否则填到left处，切换left开始移动动
 * 将left向右移动，如果指向元素比缓存元素小，则不动；否则填到right处，切换right开始移动
 * 以此往复，直到left和right重合，则将缓存元素填充到left处。此时可任务左边的数都比此时left处小，右边都比其大，之后递归遍历左右两边的数组
 *
 * @author smalle
 * @date 2020-08-22 12:58
 */
public class QuickSort {

    public static void main(String[] args) {
        int[] arr = {6, 1, 3, 7, 2, 8, 9, 5, 4};
        // int[] arr = {1, 2, 23, 6, 1,15, 3, 7, 2, 10, 8, 9, 14, 11, 22, 19, 5, 4};

        sort(arr, 0, arr.length - 1);

        for (int i = 0; i < arr.length; i++) {
            System.out.print((arr[i]) + " ");
        }
    }

    public static void sort(int[] arr, int start, int end) {
        if(arr == null || start == end) {
            return;
        }

        int left = start;
        int right = end;
        int lv = arr[start];
        boolean leftEmpty = true;
        while (left != right) {
            if(leftEmpty) {
                if(arr[right] >= lv) {
                    right--;
                } else {
                    arr[left] = arr[right];
                    left++;
                    leftEmpty = false;
                }
            } else {
                if(arr[left] <= lv) {
                    left++;
                } else {
                    arr[right] = arr[left];
                    right--;
                    leftEmpty = true;
                }
            }
        }
        arr[left] = lv;

        if(left - 1 >= start) {
            sort(arr, start, left - 1);
        }
        if(left + 1 <= end) {
            sort(arr, left + 1, end);
        }
    }
}
