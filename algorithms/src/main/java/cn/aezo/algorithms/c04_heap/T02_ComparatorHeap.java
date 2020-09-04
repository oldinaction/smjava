package cn.aezo.algorithms.c04_heap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;

/**
 * 系统提供的堆可创建简单的大根堆或小根堆，如PriorityQueue，如果修改堆中元素，是不会自动重新排序的；此时只能自定义堆
 * @author smalle
 * @date 2020-08-23 15:12
 */
public class T02_ComparatorHeap {

    public static class ComparatorHeap<T> {
        private ArrayList<T> heap;
        private HashMap<T, Integer> indexMap;
        private Comparator<T> comparator;

        public ComparatorHeap(Comparator<T> comparator) {
            this.heap = new ArrayList<>();
            this.indexMap = new HashMap<>();
            this.comparator = comparator;
        }

        public void push(T value) {
            heap.add(value);
            indexMap.put(value, heap.size() - 1);
            heapInsert(heap.size() - 1);
        }

        public T pop() {
            if(heap.size() == 0) {
                throw  new RuntimeException("无任何元素");
            }

            T ans = heap.get(0);
            swap(0, heap.size() - 1);
            heap.remove(heap.size() - 1);
            indexMap.remove(ans);
            heapify(0);
            return ans;
        }

        // 往上冒泡
        private void heapInsert(int i) {
            while (i >= 1 && comparator.compare(heap.get(i), heap.get((i - 1) / 2)) < 0) {
                swap((i - 1) / 2, i);
                i = (i - 1) / 2;
            }
        }

        // 往下沉
        private void heapify(int index) {
            int left = 2 * index + 1;
            while (left < heap.size()) {
                // 取出左右两个子节点的最大下标
                int largest = left + 1 < heap.size() && comparator.compare(heap.get(left + 1), heap.get(left)) < 0
                                ? left + 1
                                : left;
                if(comparator.compare(heap.get(index), heap.get(largest)) <= 0) {
                    break;
                }
                swap(largest, index);
                index = largest;
                left = 2 * index + 1;
            }
        }

        // 对某个值重新排序
        public void resign(T value) {
            int valueIndex = indexMap.get(value);
            // 可能往上，也可能往下，但是只会出现一种情况
            heapInsert(valueIndex);
            heapify(valueIndex);
        }

        public boolean contains(T key) {
            return indexMap.containsKey(key);
        }

        private void swap(int i, int j) {
            T o1 = heap.get(i);
            T o2 = heap.get(j);
            heap.set(i, o2);
            heap.set(j, o1);
            indexMap.put(o1, j);
            indexMap.put(o2, i);
        }
    }

    public static class Person {
        private String name;
        private int age;

        public Person(String name, int age) {
            this.name = name;
            this.age = age;
        }

        @Override
        public String toString() {
            return "Person{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    '}';
        }
    }

    public static class PersonComparator implements Comparator<Person> {
        @Override
        public int compare(Person o1, Person o2) {
            return o1.age - o2.age;
        }
    }

    public static void main(String[] args) {
        Person p1 = new Person("smalle", 18);
        Person p2 = new Person("old", 22);
        Person p3 = new Person("aezo", 20);

        // 系统提供的堆可创建简单的大根堆或小根堆
        testPriorityQueue1(p1, p2, p3);
        System.out.println("============");

        // 系统提供的堆，如果修改堆中元素，是不会自动重新排序的，此时只能自定义堆
        testPriorityQueue2(p1, p2, p3);
        System.out.println("============");

        Person p4 = new Person("smalle", 18);
        Person p5 = new Person("old", 22);
        Person p6 = new Person("aezo", 20);

        // 自定义堆
        testComparatorHeap1(p4, p5, p6);
        System.out.println("============");

        testComparatorHeap2(p4, p5, p6);

    }

    public static void testPriorityQueue1(Person ...p) {
        PriorityQueue<Person> priorityQueue = new PriorityQueue<>(new PersonComparator());
        priorityQueue.addAll(Arrays.asList(p));
        for (int i = 0; i < p.length; i++) {
            System.out.println(priorityQueue.poll());
        }
    }

    public static void testPriorityQueue2(Person ...p) {
        PriorityQueue<Person> priorityQueue = new PriorityQueue<>(new PersonComparator());
        priorityQueue.addAll(Arrays.asList(p));
        if(p.length > 1) {
            p[1].age = 10;
        }
        for (int i = 0; i < p.length; i++) {
            System.out.println(priorityQueue.poll());
        }
    }

    public static void testComparatorHeap1(Person ...p) {
        ComparatorHeap<Person> comparatorHeap = new ComparatorHeap<>(new PersonComparator());
        for (int i = 0; i < p.length; i++) {
            comparatorHeap.push(p[i]);
        }
        for (int i = 0; i < p.length; i++) {
            System.out.println(comparatorHeap.pop());
        }
    }

    public static void testComparatorHeap2(Person ...p) {
        ComparatorHeap<Person> comparatorHeap = new ComparatorHeap<>(new PersonComparator());
        for (int i = 0; i < p.length; i++) {
            comparatorHeap.push(p[i]);
        }
        if(p.length > 1) {
            p[1].age = 10;
            comparatorHeap.resign(p[1]);
        }
        for (int i = 0; i < p.length; i++) {
            System.out.println(comparatorHeap.pop());
        }
    }
}
