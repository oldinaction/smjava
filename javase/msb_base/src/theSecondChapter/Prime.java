package theSecondChapter;

public class Prime {
	//输出101到200间的质数
	//此处的continue和break的运用
	
	public static void main(String args[]){	
		for(int n=101; n<=200; n+=2){
			boolean f = true;  //不能写成成员变量
			for(int i=2; i<n; i++){
				if(n % i == 0){
					f = false;
					break;
				}
			}
					
		    if(!f){continue;}
		    
		    System.out.println(n);
		     	
		}	
	}
}
