package theEighthChapter;

import java.io.*;

public class TsetFileOutputStream {
    public static void main(String[] args) {
        int num = 0;
        int b = 0;
        FileInputStream in = null; //����FileReader��FileWriter�򲻻�����Ķ�һ�룬���ǿ����������
        FileOutputStream out = null;

        try {
            in = new FileInputStream("H:\\java\\Workspaces\\MyEclipse 10\\msb\\src\\theEighthChapter/TsetFileOutputStream.java");
            while ((b = in.read()) != -1) {
                System.out.print((char) b); //in.read()���ض�����һ���ֽڶ�Ӧ����������ǿ��ת��Ϊchar�ͣ������ļ��е�������2���ֽڣ���ֻ�ܶ�ȡһ���ֽڣ��ʲ�������ת�����������
                num++;
            }
            in.close();  //һ��Ҫ�ر��ļ�
        } catch (IOException e) {
            System.out.println("�ļ���ȡ����");
            System.exit(-1);
        }
        System.out.println("�ļ���С��" + num);

        try {
            //System.out.println(in.read());���д���⣬�������������ļ����ƴ��󡱣���������ʱ in������ʲô������
            in = new FileInputStream("H:\\java\\Workspaces\\MyEclipse 10\\msb\\src\\theEighthChapter/TsetFileOutputStream.java");
            //System.out.println(in.read());���д����,�����ȷ�����112����Ӧ������ĸp��,��in.read()�����ǵ�һ���ֽ�
            out = new FileOutputStream("H:\\java\\Workspaces\\MyEclipse 10\\msb\\TFOS.java"); //��Ӳ���ϴ�����һ��������TFOS.java�ļ�,Ӳ���ϱ�����ǰ���Ŀ¼�������ܴ���Ŀ¼
            //��System.out.println(b);�ɵ�֪��ʱ b = -1;
            while ((b = in.read()) != -1) {
                out.write(b);
            }
            in.close();
            out.close();
        } catch (IOException e) {
            System.out.println("�ļ����ƴ���");
            System.exit(-1);
        }
        System.out.println("�ļ����Ƴɹ���");  //·���¶��һ��TFOS.java�ļ�

    }
}
