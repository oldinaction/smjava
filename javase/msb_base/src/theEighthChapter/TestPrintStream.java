package theEighthChapter;

import java.io.*;
import java.util.Date;

//��ִ�еڶ������ӣ���ʱ���������û���ڵ����������д�ӡ�����̵����봫����args[0]�������������ղ���������ִ�е��������ӣ������������һ��exit����Ż�������Ĵ�����������������һ��Զ��ִ��

public class TestPrintStream {
    //�ڶ������ӵ�list������f�������ļ�·�����ַ�����p������System.out����׼������������DOS���棩
    public static void list(String f, PrintStream p) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(f));  //����������readLine()����
            String s = null;
            while ((s = br.readLine()) != null) {
                p.println(s);  //PrintStream���������println��print���������̳���FilterOutputStream��
            }
            br.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            p.println("�޷���ȡ�ļ�");
        }
    }

    public static void main(String[] args) {
        //�ڶ������ӣ�������������һ���ļ������������������ȫ����ӡ����
        String filename = args[0];  //��ȡ�������е��ַ������磺C:\\Users\\Administrator\\Desktop/XX.txt����������Ϊ��������list����
        if (filename != null) list(filename, System.out);

        //���������ӣ���־��¼����������ϵ�����ʹ�ʱ��ʱ�䡣������Ϊʲô����һֱ���뵽�ļ�������
        String s = null;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            FileWriter fw = new FileWriter("C:\\Users\\Administrator\\Desktop/TPS.txt", true);  //��Ӳ���д����ļ�
            PrintWriter log = new PrintWriter(fw);
            s = br.readLine();
            while (s != null) {
                if (s.equalsIgnoreCase("exit")) break;
                System.out.println(s.toUpperCase());
                log.println(s.toUpperCase());
                log.println(new Date());
                log.flush();
                log.close();
                s = br.readLine();
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        //��һ�����ӣ���һ���ļ������ӡ0~60000���ַ�
        PrintStream ps = null;
        try {
            FileOutputStream fos = new FileOutputStream("C:\\Users\\Administrator\\Desktop/TPS.txt");  //�����Ѿ������˴��ļ�
            ps = new PrintStream(fos);  //��װPrint��
            if (ps != null) {
                System.setOut(ps);  //���·��䡰��׼���������ԭ�������DOS����̨�У����������ps����
            }
            int num = 0;
            for (char c = 0; c <= 60000; c++) {
                System.out.print(c + " ");  //�����Ѿ�setOut�ˣ��������մ�ӡ���ļ�������
                if (num++ >= 100) {
                    System.out.println();
                    num = 0;
                }  //ÿ100������
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}




