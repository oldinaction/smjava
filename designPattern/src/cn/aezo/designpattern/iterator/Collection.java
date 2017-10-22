package cn.aezo.designpattern.iterator;

public interface Collection {
	void add(Object o);
	int size();
	Iterator iterator();
}
