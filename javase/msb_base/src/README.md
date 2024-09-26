递归的运用：
theSixthChapter -> ListFile

重写equals方法：
theThirdChapter -> Cat和TestEquals

枚举：
theSixthChapter -> TestEnum

在硬盘中创建文件：
theSixthChapter -> TestFile
theEighthChapter.* 



## 多线程 (theNinthThread)

> http://blog.csdn.net/gf771115/article/details/51682561

1. 进程和线程
    - 进程：每个进程都有独立的代码和数据空间（进程上下文），进程间的切换会有较大的开销，一个进程包含1-n个线程
    - 线程：同一类线程共享代码和数据空间，每个线程有独立的运行栈和程序计数器(PC)，线程切换开销小
    - 多进程是指操作系统能同时运行多个任务（程序），多线程是指在同一程序中有多个顺序流在执行
    - 线程和进程一样分为五个阶段：**创建、就绪、运行、阻塞、终止**
2. 在java中要想实现多线程，有两种手段：一种是继续Thread类，另外一种是实现Runable接口(常用)
3. 实例
    
    ```java
    public class Runnable1 implements Runnable{
    	public void run(){
    		for(int i=0; i<100; i++){
    			System.out.println("Runnable1在执行:" + i);
    		}
    	}
    }
 
    public static void main(String[] args) {
 		// 创建线程，此时为初始状态
        Runnable1 target = new Runnable1();
 		Thread myThread = new Thread(target);

        // 启动线程(此时线程进入可运行状态，等待CPU调用执行)
 		myThread.start(); 
 		
 		for(int i=0; i<100; i++){
 			System.out.println("main在执行：" + i);
 		}
 	}
    ```