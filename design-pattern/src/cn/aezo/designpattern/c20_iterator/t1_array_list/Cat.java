package cn.aezo.designpattern.c20_iterator.t1_array_list;

public class Cat {
	public Cat(int id) {
		super();
		this.id = id;
	}

	private int id;
	
	@Override
	public String toString() {
		return "cat:" + id;
	}
}
