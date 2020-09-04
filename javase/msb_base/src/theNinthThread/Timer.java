package theNinthThread;

public class Timer {
    private static int num = 0;

    //�����ʱ��synchronized�ؼ��֣������t2���ǵ�2������Timer2���߳�   t1���ǵ�2������Timer2���߳� (t1ִ��ʱ��t2�ж��ˣ�t2ִ����t1�ٽ���ִ��)
    public synchronized void add(String name) {
        num++;
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println(name + "���ǵ�" + num + "������Timer2���߳�");
    }

}
