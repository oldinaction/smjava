package theNinthThread;

public class MyThread extends Thread{
	//MyThread(String name)���췽����name ��ĳ���̶߳��������
	MyThread(String name){
		super(name);
	}
	public void run(){
		for(int i=0; i<3; i++){
			System.out.println("i am " + getName());  //getName()��Thread������ķ���
			try {
				sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}
	}
}
