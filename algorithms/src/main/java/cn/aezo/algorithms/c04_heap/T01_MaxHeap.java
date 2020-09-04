package cn.aezo.algorithms.c04_heap;

/**
 * 1.题目：需要组织一个大根堆，可持续往里面放一定数量(上限)的元素，每次取出元素时总返回最大的
 * 2.堆，基于数组存放：假设当前节点下标为 i, 则左子节点下表为 2i+1, 右子节点下标为 2i+2, 父节点下标为 (i-1)/2
 *
 * @author smalle
 * @date 2020-08-22 19:17
 */
public class T01_MaxHeap {

    public static class MyMaxHeap {
        private final int limit;
        private int[] heap;
        private int heapSize;

        public MyMaxHeap(int limit) {
            this.limit = limit;
            this.heap = new int[limit];
            this.heapSize = 0;
        }

        public void push(int value) {
            if(heapSize == limit) {
                throw  new RuntimeException("超出大小");
            }

            heap[heapSize] = value;
            heapInsert(heap, heapSize++);
        }

        public int pop() {
            if(heapSize == 0) {
                throw  new RuntimeException("无任何元素");
            }

            int ans = heap[0];
            swap(heap, 0, --heapSize);
            heapify(heap, 0, heapSize);
            return ans;
        }

        public boolean isEmpty() {
            return heapSize == 0;
        }

        public boolean isFull() {
            return heapSize == limit;
        }

        // 大的往上冒泡
        private void heapInsert(int[] arr, int i) {
            while (i >= 1 && arr[i] > arr[(i-1) / 2]) {
                swap(arr, (i - 1) / 2, i);
                i = (i - 1) / 2;
            }
        }

        // 大的往下沉
        private void heapify(int[] arr, int index, int heapSize) {
            int left = 2 * index + 1;
            while (left < heapSize) {
                // 取出左右两个子节点的最大下标
                int largest = left + 1 < heapSize && arr[left + 1] > arr[left] ? left + 1 : left;
                if(arr[index] >= arr[largest]) {
                    break;
                }
                swap(arr, largest, index);
                index = largest;
                left = 2 * index + 1;
            }
        }

        private void swap(int[] arr, int i, int j) {
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
    }

    // for test
    public static class RightMaxHeap {
        private int[] arr;
        private final int limit;
        private int size;

        public RightMaxHeap(int limit) {
            arr = new int[limit];
            this.limit = limit;
            size = 0;
        }

        public boolean isEmpty() {
            return size == 0;
        }

        public boolean isFull() {
            return size == limit;
        }

        public void push(int value) {
            if (size == limit) {
                throw new RuntimeException("heap is full");
            }
            arr[size++] = value;
        }

        public int pop() {
            int maxIndex = 0;
            for (int i = 1; i < size; i++) {
                if (arr[i] > arr[maxIndex]) {
                    maxIndex = i;
                }
            }
            int ans = arr[maxIndex];
            arr[maxIndex] = arr[--size];
            return ans;
        }

    }

    // for test
    public static void main(String[] args) {
        int value = 1000;
        int limit = 100;
        int testTimes = 1000000;
        for (int i = 0; i < testTimes; i++) {
            int curLimit = (int) (Math.random() * limit) + 1;
            MyMaxHeap my = new MyMaxHeap(curLimit);
            RightMaxHeap test = new RightMaxHeap(curLimit);
            int curOpTimes = (int) (Math.random() * limit);
            for (int j = 0; j < curOpTimes; j++) {
                if (my.isEmpty() != test.isEmpty()) {
                    System.out.println("Oops!");
                }
                if (my.isFull() != test.isFull()) {
                    System.out.println("Oops!");
                }
                if (my.isEmpty()) {
                    int curValue = (int) (Math.random() * value);
                    my.push(curValue);
                    test.push(curValue);
                } else if (my.isFull()) {
                    if (my.pop() != test.pop()) {
                        System.out.println("Oops!");
                    }
                } else {
                    if (Math.random() < 0.5) {
                        int curValue = (int) (Math.random() * value);
                        my.push(curValue);
                        test.push(curValue);
                    } else {
                        if (my.pop() != test.pop()) {
                            System.out.println("Oops!");
                        }
                    }
                }
            }
        }
        System.out.println("finish!");
    }



}
