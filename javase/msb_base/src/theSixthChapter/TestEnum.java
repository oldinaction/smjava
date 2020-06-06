package theSixthChapter;

public class TestEnum {
	public enum MyColor { red, green, blue };  //定义枚举类型，enum关键字后面的类型名是大写开头，用大括号，后面有分号
	
	public static void main(String[] args){
		MyColor m = MyColor.red;
		//注意switch语句，case后面有：
		switch(m){
		case red:
			System.out.println("red");
			break;
		case green:
			System.out.println("red");
			break;
		default:
			System.out.println("default");
		}
		System.out.println(m);	
	}
}
