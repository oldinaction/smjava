package theFifthChapter;
//在Dos环境下运行要把包声明去掉
//线javac NumSort.java 再java NumSort 1 2 则输出1 2  
public class NumSort {
	public static void main(String[] args){
		int[] a = new int[args.length];
		for(int i=0; i<args.length; i++){
			a[i] = Integer.parseInt(args[i]);
			
		}
		print(a);
	}
	
	private static void print(int[] a){
		for(int i=0; i<a.length; i++){
			System.out.print(a[i] + " ");
		}
		
	}

}
