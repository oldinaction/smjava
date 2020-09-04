package theEighthChapter;

import java.io.*;

public class TestDataStream {
    public static void main(String[] args) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();  //����һ������ֽڽڵ���
        DataOutputStream dos = new DataOutputStream(baos);  //��װһ��������
        try {
            dos.writeDouble(Math.random());  //�������ӳ��� д�� ���ڴ�
            dos.writeBoolean(true);
            ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
            System.out.println(bais.available());
            DataInputStream dis = new DataInputStream(bais);
            System.out.println(dis.readDouble());  //���������ڴ� ���� ����������print��ӡ�ڿ���̨
            System.out.println(dis.readBoolean());  //��д���ȶ�����readDouble()��readBoolean()��ǰ��
            dos.close();
            dis.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
