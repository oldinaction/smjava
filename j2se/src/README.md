�ݹ�����ã�
theSixthChapter -> ListFile

��дequals������
theThirdChapter -> Cat��TestEquals

ö�٣�
theSixthChapter -> TestEnum

��Ӳ���д����ļ���
theSixthChapter -> TestFile
theEighthChapter.* 



## ���߳� (theNinthThread)

> http://blog.csdn.net/gf771115/article/details/51682561

1. ���̺��߳�
    - ���̣�ÿ�����̶��ж����Ĵ�������ݿռ䣨���������ģ������̼���л����нϴ�Ŀ�����һ�����̰���1-n���߳�
    - �̣߳�ͬһ���̹߳����������ݿռ䣬ÿ���߳��ж���������ջ�ͳ��������(PC)���߳��л�����С
    - �������ָ����ϵͳ��ͬʱ���ж�����񣨳��򣩣����߳���ָ��ͬһ�������ж��˳������ִ��
    - �̺߳ͽ���һ����Ϊ����׶Σ�**���������������С���������ֹ**
2. ��java��Ҫ��ʵ�ֶ��̣߳��������ֶΣ�һ���Ǽ���Thread�࣬����һ����ʵ��Runable�ӿ�(����)
3. ʵ��
    
    ```java
    public class Runnable1 implements Runnable{
    	public void run(){
    		for(int i=0; i<100; i++){
    			System.out.println("Runnable1��ִ��:" + i);
    		}
    	}
    }
 
    public static void main(String[] args) {
 		// �����̣߳���ʱΪ��ʼ״̬
        Runnable1 target = new Runnable1();
 		Thread myThread = new Thread(target);

        // �����߳�(��ʱ�߳̽��������״̬���ȴ�CPU����ִ��)
 		myThread.start(); 
 		
 		for(int i=0; i<100; i++){
 			System.out.println("main��ִ�У�" + i);
 		}
 	}
    ```