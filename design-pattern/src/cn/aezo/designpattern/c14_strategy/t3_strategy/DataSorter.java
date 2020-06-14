package cn.aezo.designpattern.c14_strategy.t3_strategy;

public class DataSorter {
	public static void main(String[] args) {
		int[] i = new int[]{3, 4, 5, 1, 2, 6};
		DataSorter.sort(i);
		System.out.println("\n=====");

		Cat[] a = {new Cat(5, 5), new Cat(3, 3), new Cat(1, 1)};
		DataSorter.sort(a);
		DataSorter.print(a);
	}

	// 冒泡排序
	public static void sort(Object[] a) {
		for(int i=a.length; i>0; i--) {
			for(int j=0; j<i-1; j++) {
				Comparable o1 = (Comparable) a[j];
				Comparable o2 = (Comparable) a[j + 1];
				if(o1.compareTo(o2) == 1) {
					swap(a, j , j+1);
				}
			}
		}
	}

	private static void swap(Object[] a, int x, int y) {
		Object temp = a[x];
		a[x] = a[y];
		a[y] = temp;
	}

	public static void print(Object[] a) {
		for(int i=0; i<a.length; i++) {
			System.out.print(a[i] + " ");
		}
	}

	// 排序整数
	public static void sort(int[] a) {
		for(int i=a.length; i>0; i--) {
			for(int j=0; j<i-1; j++) {
				if(a[j] > a[j+1]) {
					int temp = a[j];
					a[j] = a[j+1];
					a[j+1] = temp;
				}
			}
		}

		for(int i=0; i<a.length; i++) {
			System.out.print(a[i] + " ");
		}
	}
}
