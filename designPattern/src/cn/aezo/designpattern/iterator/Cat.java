package cn.aezo.designpattern.iterator;

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
