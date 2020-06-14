package cn.aezo.designpattern.c20_iterator.t1_array_list;

/**
 * 结果：
 *
 * 15
 * cat:0 cat:1 cat:2 cat:3 cat:4 cat:5 cat:6 cat:7 cat:8 cat:9 cat:10 cat:11 cat:12 cat:13 cat:14
 */
public class Main {
	public static void main(String[] args) {
		MyCollection c = new MyArrayList();
		for(int i=0; i<15; i++) {
			c.add(new Cat(i));
		}
		System.out.println(c.size());
		
		MyIterator it = c.iterator();
		while(it.hasNext()) {
			Object o = it.next();
			System.out.print(o + " ");
		}
	}
}


