package theFifthChapter;

public class BubbleSort {
    public static void main(String[] args) {
        Date[] days = new Date[5];
        days[0] = new Date(2006, 5, 4);
        days[1] = new Date(2006, 7, 4);
        days[2] = new Date(2008, 5, 4);
        days[3] = new Date(2004, 5, 9);
        days[4] = new Date(2004, 5, 4);

        bubbleSort(days);

        for (int i = 0; i < days.length; i++) {
            System.out.println(days[i]);
        }
    }

    //ð�ݷ�����ע���� i--
    public static Date[] bubbleSort(Date[] a) {
        int len = a.length;
        for (int i = len - 1; i >= 1; i--) {
            for (int j = 0; j <= i - 1; j++) {
                if (a[j].compare(a[j + 1]) > 0) {
                    Date temp = a[j];
                    a[j] = a[j + 1];
                    a[j + 1] = temp;
                }
            }
        }
        return a;
    }
}
