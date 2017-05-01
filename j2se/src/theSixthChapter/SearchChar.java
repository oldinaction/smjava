package theSixthChapter;

public class SearchChar {
	public static void main(String[] args){
		String s = new String("java12bbjava5JAVAjava52jav1java4va");
		
		char[] arr = s.toCharArray();
		count(arr);
		searchString(s, "java");
		
	}
	
	public static void count(char[] a){
		int LCount = 0;
		int UCount = 0;
		int OCount = 0;
		
		for(int i=0; i<a.length; i++){
			if(a[i]>='a' && a[i]<='z') LCount++;
			else if(a[i]>='A' && a[i]<='Z') UCount++;
			else OCount++;	
		}
		
		System.out.println(LCount + ","  + UCount + ","  + OCount);
	}
	
	
	public static void searchString(String s, String sToFind){
		int count = 0;
		int index = -1;
		
		while((index = s.indexOf(sToFind)) != -1){
			s = s.substring(index + sToFind.length());
			count ++;
		}
		System.out.println(count);	
	}

}
