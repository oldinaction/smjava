package theThirdChapter;

public class TestEquals {
	public static void main(String args[]){
	Cat c1 = new Cat(1, 2, 3);
	Cat c2 = new Cat(1, 2, 3);
	System.out.println(c1 == c2); //c1 == c2比较的是c1 和 c2的引用是否相同，每new一个对象，引用都是不同的
	System.out.println(c1.equals(c2)); //因为Cat类中重写了Object类中的equals方法，重写后，比较的是c1 和 c2的内容是否相同；如果没重写，则效果c1 == c2比较的是c1 和 c2的引用是否相同c1 == c2一样比较的是c1 和 c2的引用是否相同
	
	String s1 = new String("hello");
	String s2 = new String("hello");
	System.out.println(s1 == s2);
	System.out.println(s1.equals(s2)); //最终返回时true，因为String类里面也重写了equals方法，参见API文档中的String类
	}
}
