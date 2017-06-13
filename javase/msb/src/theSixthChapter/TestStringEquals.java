package theSixthChapter;

public class TestStringEquals {
	public static void main(String[] args){
		String s1 = "hello";
		String s2 = "world";
		String s3 = "hello";
		
		System.out.println(s1 == s3); //true
		
		s1 = new String("hello");
		s2 = new String("hello");
		System.out.println(s1 == s2); //false， 比较的是地址
		System.out.println(s1.equals(s2)); //true, 因为String类重写了Object类中的equals方法
	}

}
