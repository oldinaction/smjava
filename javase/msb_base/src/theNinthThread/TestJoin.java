package theNinthThread;

public class TestJoin {
	public static void main(String[] args){
		MyThread myThread = new MyThread("oldinaction");
		myThread.start();  //先要让线程准备就绪
		try {
			myThread.join();  //等待该线程终止
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(int i=0; i<3; i++){
			System.out.println("i am main thread");
		}
	}

}
