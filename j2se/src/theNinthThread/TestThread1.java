package theNinthThread;

public class TestThread1 {
	public static void main(String[] args){
		Runnable1 target = new Runnable1();
		Thread myThread = new Thread(target);
		myThread.start();  //调用的start()是Thread类的方法，此时不能直接写myThread.run();这样是直接调用了run方法。线程启动后，CPU交替运行线程，故交替输出
		
		for(int i=0; i<100; i++){
			System.out.println("main在执行：" + i);
		}
	}
}

/*
 * 第二种创建线程方法
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
