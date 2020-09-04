package theNinthThread;

public class TestJoin {
    public static void main(String[] args) {
        MyThread myThread = new MyThread("oldinaction");
        myThread.start();  //��Ҫ���߳�׼������
        try {
            myThread.join();  //�ȴ����߳���ֹ
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        for (int i = 0; i < 3; i++) {
            System.out.println("i am main thread");
        }
    }

}
