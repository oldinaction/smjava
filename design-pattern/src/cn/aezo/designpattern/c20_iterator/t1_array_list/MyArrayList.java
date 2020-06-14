package cn.aezo.designpattern.c20_iterator.t1_array_list;

/**
 * 基于数组实现容器
 */
public class MyArrayList implements MyCollection {
	Object[] objects = new Object[10];
	int index = 0;

	public void add(Object o) {
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
	
	public MyIterator iterator() {
		return new MyArrayListIterator();
	}

	/**
	 * 内部类实现遍历接口
	 */
	private class MyArrayListIterator implements MyIterator {
		private int currentIndex = 0;

		@Override
		public boolean hasNext() {
			if(currentIndex >= index) return false;
			else return true;
		}

		@Override
		public Object next() {
			Object o = objects[currentIndex];
			currentIndex ++;
			return o;
		}
		
	}
}
