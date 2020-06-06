package theNinthThread;

public class TestSync implements Runnable{
	Timer timer = new Timer();
	public static void main(String[] args){
		TestSync ts= new TestSync();
		Thread t1 = new Thread(ts);
		Thread t2 = new Thread(ts);
		t1.setName("t1"); 
	    t2.setName("t2");
		t1.start();
		t2.start();
	}
	
	public void run(){
		timer.add(Thread.currentThread().getName());
	}

}
