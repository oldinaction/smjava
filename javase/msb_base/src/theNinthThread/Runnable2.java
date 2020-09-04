package theNinthThread;

import java.util.Date;

public class Runnable2 implements Runnable {
    //������run�������׳��쳣��ֻ����try catch ������Ϊ������д��run�������ʲ����׳���ԭ������ͬ���쳣
    public void run() {
        boolean flag = true;
        while (flag) {
            System.out.println(new Date());
            try {
                Thread.sleep(1000);     //����ֱ��дsleep(1000)���Ǹ��̵߳���sleep()���Ǹ��߳̾�˯��
            } catch (InterruptedException e) {
                return;
            }
        }
    }
}
