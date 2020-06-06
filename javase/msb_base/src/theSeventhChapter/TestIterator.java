package theSeventhChapter;
import java.util.*;

public class TestIterator {
	public static void main(String[] args){
		Collection c = new HashSet();  //父类引用指向子类对象
		c.add(new Name("aaaa", "bbb"));
		c.add(new Name("ccc", "dd"));
		c.add(new Name("ee", "f"));
		Iterator i = c.iterator();  //c.iterator()返回在此 collection 的元素上进行迭代的迭代器。
		
		while(i.hasNext()){
			Name n = (Name) i.next();
			System.out.print(n.getFirstName() + "  ");
		}
		System.out.println();
		//注意此for循环
		for(i = c.iterator(); i.hasNext(); ){
			Name n = (Name) i.next();  //必须强制转换
			if(n.getFirstName().length() < 3){
				i.remove();  //i.remove()从迭代器指向的 collection 中移除迭代器返回的最后一个元素
			}
		}
		System.out.println(c);
	}
}
