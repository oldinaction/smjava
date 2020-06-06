package theNinthThread;

public class MyThread extends Thread{
	//MyThread(String name)构造方法，name 是某个线程对象的名字
	MyThread(String name){
		super(name);
	}
	public void run(){
		for(int i=0; i<3; i++){
			System.out.println("i am " + getName());  //getName()是Thread类里面的方法
			try {
				sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}
	}
}
