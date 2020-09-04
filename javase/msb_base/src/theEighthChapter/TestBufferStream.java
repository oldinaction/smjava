package theEighthChapter;

import java.io.*;

public class TestBufferStream {
    public static void main(String[] args) {
        try {
            int c = 0;
            FileInputStream fis = new FileInputStream("H:\\java\\Workspaces\\MyEclipse 10\\msb\\src\\theEighthChapter\\TestBufferStream.java");
            BufferedInputStream bis = new BufferedInputStream(fis);
            System.out.println(bis.read());

            //��Ϊǰ�����p����ַ������������a��ʼ����
            bis.mark(100);  //�ڴ��������б�ǵ�ǰ��λ��,���������ֵ�Ƕ��������ڵ�ǰλ��
            for (int i = 0; i < 10 && (c = bis.read()) != -1; i++) {
                System.out.print((char) c + " ");
            }
            System.out.println();

            bis.reset();
            for (int i = 0; i < 10 && (c = bis.read()) != -1; i++) {
                System.out.print((char) c + " ");
                System.out.print(c + " ");
                System.out.print(",");
            }

            bis.close();

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println();

        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("H:\\java\\Workspaces\\MyEclipse 10\\msb/TBS.txt"));  //��Ӳ���ϴ�����һ��������TBS.java�ļ�
            BufferedReader br = new BufferedReader(new FileReader("H:\\java\\Workspaces\\MyEclipse 10\\msb/TBS.txt"));
            String s = null;
            for (int i = 0; i < 100; i++) {
                s = String.valueOf(Math.random());  //��double�͵������ת��Ϊ�ַ���
                bw.write(s);
                bw.newLine();  //д��һ���зָ���
            }
            bw.flush();
            while ((s = br.readLine()) != null) {
                System.out.println(s);
            }  //readLine()��ȡһ���ַ���
            bw.close();
            br.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
