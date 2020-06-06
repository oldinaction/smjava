package theNinthThread;

public class TestInterrupt {
	public static void main(String[] args){
		Runnable2 runnable = new Runnable2();
		Thread myThread = new Thread(runnable);
		myThread.start();
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		myThread.interrupt(); 	
	}
}
