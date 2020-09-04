package theEighthChapter;

import java.io.*;

public class TestConverting {
    public static void main(String[] args) {
        try {
            OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream("H:\\java\\Workspaces\\MyEclipse 10/msb/TC.txt"));  //ת����
            osw.write("oldinaction");
            System.out.println(osw.getEncoding());  //getEncoding()���ش���ʹ�õ��ַ���������ơ���ʱĬ��Ϊϵͳ���루win���İ�ΪGBK��
            osw.close();
            osw = new OutputStreamWriter(new FileOutputStream("H:\\java\\Workspaces\\MyEclipse 10/msb/TC.txt", true), "ISO8859_1");  //����ڶ�������Ϊ true�����ֽ�д���ļ�ĩβ����������д���ļ���ʼ����"ISO8859_1"��һ�����ı���
            osw.write('\t' + "oldinaction");  //'\t'��Tab��ת���ַ�
            System.out.println(osw.getEncoding());
            osw.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        InputStreamReader isr = new InputStreamReader(System.in);  //��װһ��ת���������ֽ���ת�����ַ�����System.in API�ĵ�����һ��InputStream�����ܼ��̵�����
        BufferedReader br = new BufferedReader(isr);  //��װһ��������
        String s = null;
        try {
            s = br.readLine();  //����������readLine()
            while (s != null) {
                if (s.equalsIgnoreCase("exit")) break;
                System.out.println(s.toUpperCase());
                br.close();
                s = br.readLine();  //ÿ����һ��readLine()��Ҫ����һ��System.in������enterǰ��һ�����룩��������������
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
