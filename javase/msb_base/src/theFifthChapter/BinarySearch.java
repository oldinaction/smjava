package theFifthChapter;

public class BinarySearch {
    public static void main(String[] args) {
        int[] m = {1, 3, 6, 8, 9, 10, 12, 18, 20, 34};
        int i = 12;
        System.out.println("�������еĵ�ַ��" + binarySearch(m, i));
    }

    public static int binarySearch(int[] a, int num) {
        int startNum = 0;
        int endNum = a.length - 1;
        int m = (startNum + endNum) / 2;

        if (m == 0) return -1;
        while (startNum <= endNum) {
            if (num == a[m]) return m;
            if (num > a[m]) {
                startNum = m + 1;
            }
            if (num < a[m]) {
                endNum = m - 1;
            }
            m = (startNum + endNum) / 2;
        }
        return -1;

    }

}
