package theNinthThread;

public class TestThread1 {
    public static void main(String[] args) {
        Runnable1 target = new Runnable1();
        Thread myThread = new Thread(target);
        myThread.start();  //���õ�start()��Thread��ķ�������ʱ����ֱ��дmyThread.run();������ֱ�ӵ�����run�������߳�������CPU���������̣߳��ʽ������

        for (int i = 0; i < 100; i++) {
            System.out.println("main��ִ�У�" + i);
        }
    }
}

/*
 * �ڶ��ִ����̷߳���
public class TestThread1 {
	public static void main(String args[]) {
		MyThread myThread = new MyThread();
		myThread.start();
		
		for(int i=0; i<100; i++) {
			System.out.println("Main Thread:------" + i);
		}
	}
}

class MyThread extends Thread {
	public void run() {  
		for(int i=0; i<100; i++) {	
			System.out.println("Runner1 :" + i);
		}
	}
}
*/
