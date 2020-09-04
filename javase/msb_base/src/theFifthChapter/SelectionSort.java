package theFifthChapter;

public class SelectionSort {
    static int[] a = {2, 5, 6, 1, 9, 8, 3, 4, 7};

    public static void main(String[] args) {
        print(a);
        selectionSort(a);
        print(a);
    }

    //ѡ��������
    public static void selectionSort(int[] a) {
        for (int i = 0; i < a.length; i++) {
            for (int j = i + 1; j < a.length; j++) {
                if (a[j] < a[i]) {
                    int temp = a[i];
                    a[i] = a[j];
                    a[j] = temp;
                }
            }
        }
    }
/*
	      ���õ�д����
	   public static void selectionSort(int[] a) {
 	     int k, temp;
		 for(int i=0; i<a.length; i++){
			k = i;
			for(int j=k+1; j<a.length; j++){
				if(a[j] < a[k]){
					k = j;
				}
			}
			if(k != i){
				temp = a[i];
				a[i] = a[k];
				a[k] = temp;
			}
		}
	}
*/

    public static void print(int[] a) {
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }
        System.out.println();
    }

}
