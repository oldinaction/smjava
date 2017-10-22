package cn.aezo.designpattern.iterator;

public class Test {
	public static void main(String[] args) {
		Collection c = new ArrayList();
		for(int i=0; i<15; i++) {
			c.add(new Cat(i));
		}
		System.out.println(c.size());
		
		Iterator it = c.iterator();
		while(it.hasNext()) {
			Object o = it.next();
			System.out.print(o + " ");
		}
	}
}


