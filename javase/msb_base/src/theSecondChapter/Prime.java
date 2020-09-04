package theSecondChapter;

public class Prime {
    //���101��200�������
    //�˴���continue��break������

    public static void main(String args[]) {
        for (int n = 101; n <= 200; n += 2) {
            boolean f = true;  //����д�ɳ�Ա����
            for (int i = 2; i < n; i++) {
                if (n % i == 0) {
                    f = false;
                    break;
                }
            }

            if (!f) {
                continue;
            }

            System.out.println(n);

        }
    }
}
