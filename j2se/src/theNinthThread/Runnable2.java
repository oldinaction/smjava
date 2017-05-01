package theNinthThread;

import java.util.Date;

public class Runnable2 implements Runnable{
	//不能再run方法中抛出异常，只能用try catch 处理，因为这是重写的run方法，故不能抛出与原方法不同的异常
	public void run(){
		boolean flag = true;
		while(flag){
			System.out.println(new Date());
			try{
				Thread.sleep(1000);	 //不能直接写sleep(1000)，那个线程调用sleep()，那个线程就睡眠
			}catch(InterruptedException e){
				return;
			}	
		}
	}
}
