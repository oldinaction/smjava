package theFifthChapter;

//��500����վ��һȦ������������1,2,3��������3���Ǹ����˳�������������������һ����ԭ��վ��λ��
public class Count3Quite {
    public static void main(String[] args) {
        boolean[] arr = new boolean[500];
        for (int i = 0; i < arr.length; i++) {
            //�������е��˶���Ȧ�ڣ�true)
            arr[i] = true;
        }
        //ʣ�µ�����leftCount
        int leftCount = arr.length;
        //������countNum
        int countNum = 0;
        //��ַ��0��ʼ
        int index = 0;
        //���ʣ�µ���������1����ôһֱ������ȥ
        while (leftCount > 1) {
            //�����ַΪindex�����
            if (arr[index] == true) {
                countNum++; //��������Ȧ��ʱ����������1
                if (countNum == 3) {
                    countNum = 0; //���Ƶ�3ʱ�͹���
                    arr[index] = false; //�����˳�Ȧ�ڣ�false��,ʣ�µ��˼�1
                    leftCount--;
                }
            }
            //��ִ����һ����
            index++;
            //���������Ǹ��������һ������������һȦ��Ȼ�󽫴˵�ַ����
            //arr.length��Զ������Ϊ500���˳����Ǹ���ֻ�Ǹ�ֵΪfalse�����жϹ����л����ж���ԭ���˳�����
            if (index == arr.length) {
                index = 0;
            }
        }

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == true) {
                System.out.println("���һ����ԭ����λ����" + (i + 1));
            }
        }

    }
}
