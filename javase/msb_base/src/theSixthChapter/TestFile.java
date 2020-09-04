package theSixthChapter;

import java.io.File;
import java.io.IOException;

//�����java�ļ���һ�����У������ҵ�������Ķ�����Ŀ¼���������������Ŀ¼�ĸ�Ŀ¼�´�������·������������java�ļ��ĸ�Ŀ¼�´���
//�������ļ������ڣ���һ�����п���̨�޷�Ӧ���ڶ����������
public class TestFile {
    public static void main(String[] args) {
        String separator = File.separator;  //��API�ĵ�separator��File���һ��final���ԣ�ָ�ָ��������磺'\'��
        String filename = "myFile.txt";
        String directory = "myFile1" + separator + "myFile2";
        File f = new File(directory, filename); //File(String parent, String child) ���� parent ·�����ַ����� child ·�����ַ�������һ���� File ʵ����

        if (f.exists()) {
            System.out.println("�ļ���" + f.getAbsolutePath()); //getAbsolutePath()���ش˳���·�����ľ���·�����ַ�����(���ļ���Ӳ�̵��Ǹ�λ��)
            System.out.println("�ļ���С" + f.length());
        } else {
            f.getParentFile().mkdirs(); //mkdirs()�����˳���·����ָ����Ŀ¼���������б��赫�����ڵĸ�Ŀ¼����ΪmyFile1����myFile2��������һϵ�е�
            try {
                f.createNewFile(); //createNewFile()���ָ�����ļ������ڲ��ɹ��ش������򷵻� true�����ָ�����ļ��Ѿ����ڣ��򷵻� false
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
