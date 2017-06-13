package theNinthThread;

public class Runnable1 implements Runnable{
	public void run(){
		for(int i=0; i<100; i++){
			System.out.println("Runnable1ÔÚÖ´ÐÐ:" + i);
		}
	}
}
