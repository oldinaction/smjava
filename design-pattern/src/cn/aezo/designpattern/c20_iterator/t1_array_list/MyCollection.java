package cn.aezo.designpattern.c20_iterator.t1_array_list;

public interface MyCollection {
	void add(Object o);
	int size();
	MyIterator iterator();
}
