package theSixthChapter;

//���ַ���"1, 2; 3, 4, 5; 6, 7, 8"�÷ָ����ֿ����ٷŵ�һ����ά��double��������
public class ArrayForStr {
    public static void main(String[] args) {
        double[][] d;
        String s = "1, 2; 3, 4, 5; 6, 7, 8";
        String[] sFirst = s.split(";");  //��;�ֳ���һά
        d = new double[sFirst.length][]; //����һά�ĳ��ȸ���double������ĵ�һά����ʱdouble����������������

        for (int i = 0; i < sFirst.length; i++) {
            String[] sSecond = sFirst[i].split(",");  //��,�ֳ��ڶ�ά
            d[i] = new double[sSecond.length]; //���ڶ�ά�ĳ��ȸ���double������ĵڶ�ά����ʱdouble����ֻ��һ��������
            for (int j = 0; j < sSecond.length; j++) {
                d[i][j] = Double.parseDouble(sSecond[j]); // ����һ���µ� double ֵ����ֵ����ʼ��Ϊ��ָ�� String ��ʾ��ֵ������ Double ��� valueOf ����һ����
            }
        }

        for (int i = 0; i < d.length; i++) {
            for (int j = 0; j < d[i].length; j++) {
                System.out.println(d[i][j] + " ");
            }
            System.out.println();
        }

    }

}
