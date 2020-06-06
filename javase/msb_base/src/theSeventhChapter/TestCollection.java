package theSeventhChapter;

import java.util.*;

public class TestCollection {
	public static void main(String[] args){
		Collection c = new ArrayList();  //ArrayList是Collection的子类。可以放入不同类型的对象
		c.add("hello");
		c.add(new Integer(100));
		System.out.println(c.size());
		System.out.println(c);
	}

}
