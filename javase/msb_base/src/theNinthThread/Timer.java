package theNinthThread;

public class Timer {
	private static int num = 0;
	//如果此时无synchronized关键字，则输出t2你是第2个调用Timer2的线程   t1你是第2个调用Timer2的线程 (t1执行时被t2中断了，t2执行完t1再接着执行)
	public synchronized void add(String name){
		num++;
		try {
			Thread.sleep(1);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(name + "你是第" + num + "个调用Timer2的线程");
	}

}
