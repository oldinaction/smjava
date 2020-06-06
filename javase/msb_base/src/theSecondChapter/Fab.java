package theSecondChapter;

public class Fab {
	
	//Feb数列：1 1 2 3 5 8 13 21 。。。。。。
	
	public static void main(String args[]){
		System.out.println(f(40));
	}
	
	public static long f(int index){
		
		if(index < 1){
			System.out.println("无效参数，您应该传入大于1的参数");
			return -1;
		}
		
		if(index == 1 || index == 2){
			return 1;
		}
		
		long f1 = 1L;
		long f2 = 1L;
		long f = 0;
		
		for(int i=0; i<index-2; i++){
			f = f1 + f2;
			f1 = f2;
			f2 = f;
		}
		
		return f;
		
	}

}
