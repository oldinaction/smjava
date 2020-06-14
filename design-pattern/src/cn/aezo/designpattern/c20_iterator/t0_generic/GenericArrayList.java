package cn.aezo.designpattern.c20_iterator.t0_generic;

/**
 * 基于泛型的容器
 * @param <E>
 */
public class GenericArrayList<E> {
	Object[] objects = new Object[10];
	int index = 0;

	public void add(E o) {
		if(index == objects.length) {
			Object[] newObjects = new Object[objects.length * 2];
			System.arraycopy(objects, 0, newObjects, 0, objects.length);
			objects = newObjects;
		}
		objects[index] = o;
		index ++;
	}
	
	public int size() {
		return index;
	}
	
	public static void main(String args[]) {
		GenericArrayList<String> a = new GenericArrayList<String>();
		a.add("hello");
		System.out.println(a.size());
	}
}
