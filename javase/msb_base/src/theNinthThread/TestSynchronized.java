package theNinthThread;

//�����⣿����Thread.sleep(5000);������Ӱ����������Thread.sleep(5000);����ĺ�����Ӱ����������
public class TestSynchronized implements Runnable {
    int b = 100;

    public synchronized void m1() throws Exception {
        b = 1000;
        //Thread.sleep(5000);
        System.out.println("b = " + b);
    }

    public synchronized void m2() throws Exception {
        //Thread.sleep(2500);
        b = 2000;
    }

    public void run() {
        try {
            m1();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
        TestSynchronized ts = new TestSynchronized();
        Thread t = new Thread(ts);
        t.start();

        ts.m2();
        System.out.println(ts.b);
    }
}